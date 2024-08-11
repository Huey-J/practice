package com.pay.remittance.application.service;

import com.pay.common.UseCase;
import com.pay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.pay.remittance.adapter.out.persistence.RemittanceRequestMapper;
import com.pay.remittance.application.port.RequestRemittancePort;
import com.pay.remittance.application.port.out.banking.BankingPort;
import com.pay.remittance.application.port.out.membership.MembershipPort;
import com.pay.remittance.application.port.out.membership.MembershipStatus;
import com.pay.remittance.application.port.out.money.MoneyInfo;
import com.pay.remittance.application.port.out.money.MoneyPort;
import com.pay.remittance.domain.RemittanceRequest;
import com.pay.remittance.application.port.in.RequestRemittanceCommand;
import com.pay.remittance.application.port.in.RequestRemittanceUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestRemittanceService implements RequestRemittanceUseCase {

  private final MembershipPort membershipPort;
  private final RequestRemittancePort requestRemittancePort;
  private final RemittanceRequestMapper remittanceRequestMapper;
  private final MoneyPort moneyPort;
  private final BankingPort bankingPort;

  // 송금 요청
  @Override
  public RemittanceRequest requestRemittance(RequestRemittanceCommand command) {

    // 0. 송금 요청 상태를 시작 상태로 기록 (persistence layer)
    RemittanceRequestJpaEntity remittanceRequestHistory = requestRemittancePort
        .createRemittanceRequestHistory(command);

    // 1. from 멤버십 상태 확인 (membership Svc)
    MembershipStatus membershipStatus = membershipPort.getMembershipStatus(
        command.getFromMembershipId());
    if (!membershipStatus.isValid()) {
      return null;  // TODO Exception 처리
    }

    // 2. 잔액 존재하는지 확인 (money svc)
    MoneyInfo moneyInfo = moneyPort.getMoneyInfo(command.getFromMembershipId());

    // 2-1. 잔액이 충분하지 않다면, 충전 요청 (money svc)
    if (moneyInfo.getBalance() < command.getAmount()) {
      moneyPort.requestMoneyRecharging(command.getFromMembershipId(),
          command.getAmount() - moneyInfo.getBalance());
    }

    // 잔액이 충분치 않은 경우
    if(moneyInfo.getBalance() < command.getAmount()) {
      // 만원 단위로 올림하는 Math 함수
      int rechargeAmount = (int) Math.ceil((command.getAmount() - moneyInfo.getBalance()) / 10000.0) * 10000;

      // 2-1. 잔액이 충분하지 않다면, 충전 요청 (money svc)
      boolean moneyResult = moneyPort.requestMoneyRecharging(command.getFromMembershipId(), rechargeAmount);
      if (!moneyResult){
        return null;  // TODO Exception 처리
      }
    }

    // 3. 송금 타입 (고객/은행)
    if (command.getRemittanceType() == 0) {
      // 3-1. 내부 고객일 경우
      // from 고객 머니 감액, to 고객 머니 증액 (money svc)
      boolean remittanceResult1 = moneyPort
          .requestMoneyDecrease(command.getFromMembershipId(), command.getAmount());
      boolean remittanceResult2 = moneyPort
          .requestMoneyIncrease(command.getToMembershipId(), command.getAmount());

      if (!remittanceResult1 || !remittanceResult2) {
        return null;  // TODO Exception 처리
      }
    } else if (command.getRemittanceType() == 1) {
      // 3-2. 외부 은행 계좌
      // 외부 은행 계좌가 적절한지 확인 (banking svc)
      // 법인계좌 -> 외부 은행 계좌 펌뱅킹 요청 (banking svc)
      boolean remittanceResult = bankingPort.requestFirmbanking(
          command.getToBankName(), command.getToBankAccountNumber(), command.getAmount());
      if (!remittanceResult) {
        return null;  // TODO Exception 처리
      }
    }

    // 4. 송금 요청 상태를 성공으로 기록 (persistence layer)
    remittanceRequestHistory.setRemittanceStatus("success");
    boolean result = requestRemittancePort.saveRemittanceRequestHistory(remittanceRequestHistory);

    if (!result) {
      // TODO Exception 처리
      return null;
    }
    return remittanceRequestMapper.mapToDomainEntity(remittanceRequestHistory);
  }
}
