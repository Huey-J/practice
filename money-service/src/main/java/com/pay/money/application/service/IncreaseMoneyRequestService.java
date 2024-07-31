package com.pay.money.application.service;

import com.pay.common.ChargingMoneyTask;
import com.pay.common.CountDownLatchManager;
import com.pay.common.SubTask;
import com.pay.common.UseCase;
import com.pay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.pay.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.pay.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.pay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.pay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.pay.money.application.port.out.GetMembershipPort;
import com.pay.money.application.port.out.IncreaseMoneyPort;
import com.pay.money.application.port.out.Membership;
import com.pay.money.application.port.out.SendChargingMoneyTaskPort;
import com.pay.money.domain.MoneyChangingRequest;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {

  private final CountDownLatchManager countDownLatchManager;
  private final SendChargingMoneyTaskPort sendChargingMoneyTaskPort;
  private final GetMembershipPort getMembershipPort;
  private final IncreaseMoneyPort increaseMoneyPort;
  private final MoneyChangingRequestMapper mapper;

  @Override
  public MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command) {
    // 머니의 충전(증액) 과정

    // 1. 고객 정보가 정상인지 확인 (member-service)
    Membership membership = getMembershipPort.getMembership(command.getTargetMembershipId());
    if (!membership.isValid()) {
      return null;
    }

    // TODO
    //  2. 고객의 연동된 계좌가 있는지, 고객의 연동된 계좌의 잔액이 충분한지도 확인 (banking-service)
    //  3. 법인 계좌 상태도 정상인지 확인 (banking-service)
    //  4. 증액을 위한 "기록". 요청 상태로 MoneyChangingRequest 를 생성한다. (MoneyChangingRequest)
    //  5. 펌뱅킹을 수행하고 (고객의 연동된 계좌 -> 패캠페이 법인 계좌) (banking-service)


    // 6-1. 결과가 정상적이라면. 성공으로 MoneyChangingRequest 상태값을 변동 후에 리턴
    //  성공 시 멤버의 MemberMoney 값 증액
    // 6-2. 결과가 실패라면, 실패라고 MoneyChangingRequest 상태값을 변동 후에 리턴
    MemberMoneyJpaEntity memberMoney = increaseMoneyPort.increaseMoney(
        command.getTargetMembershipId(), command.getAmount());

    if (memberMoney != null) {
      MoneyChangingRequestJpaEntity moneyChangingRequest = increaseMoneyPort.createMoneyChangingRequest(
          command.getTargetMembershipId(),
          0,  // 증액
          command.getAmount(),
          1   // 성공
      );
      return mapper.mapToDomainEntity(moneyChangingRequest);
    }
    return null;
  }

  @Override
  public MoneyChangingRequest increaseMoneyRequestAsync(IncreaseMoneyRequestCommand command) {
    // 1. SubTask 생성
    // 멤버십 유효성 검사
    SubTask validateMembershipIdTask = SubTask.builder()
        .subTaskName("validateMembershipId")
        .membershipID(command.getTargetMembershipId())
        .taskType("membership")
        .status("ready")
        .build();

    // 뱅킹 유효성 검사
    SubTask validateBankingTask = SubTask.builder()
        .subTaskName("validateBanking")
        .membershipID(command.getTargetMembershipId())
        .taskType("banking")
        .status("ready")
        .build();

    // TODO FirmBanking SubTask 요청
//    SubTask requestFirmBankingTask = SubTask.builder()

    List<SubTask> subTaskList = new ArrayList<>();
    subTaskList.add(validateMembershipIdTask);
    subTaskList.add(validateBankingTask);

    // 2. Task 생성 - 증액 Task
    ChargingMoneyTask task = ChargingMoneyTask.builder()
        .taskID(UUID.randomUUID().toString())
        .taskName("chargingMoneyTask")
        .subTaskList(subTaskList)
        .moneyAmount(command.getAmount())
        .membershipID(command.getTargetMembershipId())
        .toBankName("hello world bank")
        .build();

    // 2. Kafka Cluster 에 Topic Produce
    sendChargingMoneyTaskPort.sendChargingMoneyTask(task);
    countDownLatchManager.addCountDownLatch(task.getTaskID());

    // 3. Wait
    try {
      // consumer 가 모든 task 완료(count down) 시 스레드 다시 시작
      countDownLatchManager.getCountDownLatch(task.getTaskID()).await();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    // 4. TaskConsumer - 등록된 Task, SubTask를 확인
    String result = countDownLatchManager.getDataForKey(task.getTaskID());
    if (result. equals ("success")) {
      // 4-1. Consume ok, Logic
      MemberMoneyJpaEntity memberMoney = increaseMoneyPort.increaseMoney(
          command.getTargetMembershipId(), command.getAmount());

      if (memberMoney != null) {
        MoneyChangingRequestJpaEntity moneyChangingRequest = increaseMoneyPort.createMoneyChangingRequest(
            command.getTargetMembershipId(),
            0,  // 증액
            command.getAmount(),
            1   // 성공
        );
        return mapper.mapToDomainEntity(moneyChangingRequest);
      }
    }

    return null;
  }
}
