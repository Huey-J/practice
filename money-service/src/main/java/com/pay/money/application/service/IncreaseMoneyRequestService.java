package com.pay.money.application.service;

import com.pay.common.UseCase;
import com.pay.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.pay.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.pay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.pay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.pay.money.application.port.out.IncreaseMoneyPort;
import com.pay.money.domain.MoneyChangingRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {

  private final IncreaseMoneyPort increaseMoneyPort;
  private final MoneyChangingRequestMapper mapper;

  @Override
  public MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command) {
    // TODO 머니의 충전(증액) 과정
    //  1. 고객 정보가 정상인지 확인 (member-service)
    //  2. 고객의 연동된 계좌가 있는지, 고객의 연동된 계좌의 잔액이 충분한지도 확인 (banking-service)
    //  3. 법인 계좌 상태도 정상인지 확인 (banking-service)
    //  4. 증액을 위한 "기록". 요청 상태로 MoneyChangingRequest 를 생성한다. (MoneyChangingRequest)
    //  5. 펌뱅킹을 수행하고 (고객의 연동된 계좌 -> 패캠페이 법인 계좌) (banking-service)
    //  6-1. 결과가 정상적이라면. 성공으로 MoneyChangingRequest 상태값을 변동 후에 리턴
    //   성공 시 멤버의 MemberMoney 값 증액
    //  6-2. 결과가 실패라면, 실패라고 MoneyChangingRequest 상태값을 변동 후에 리턴

    MoneyChangingRequestJpaEntity moneyChangingRequest = increaseMoneyPort.createMoneyChangingRequest(
        command.getTargetMembershipId(),
        0,
        command.getAmount(),
        0
    );

    return mapper.mapToDomainEntity(moneyChangingRequest);
  }

}
