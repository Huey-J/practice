package com.pay.remittance.application.service;

import com.pay.common.UseCase;
import com.pay.remittance.application.port.out.membership.MembershipPort;
import com.pay.remittance.application.port.out.membership.MembershipStatus;
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

  // 송금 요청
  @Override
  public RemittanceRequest requestRemittance(RequestRemittanceCommand command) {

    // 0. 송금 요청 상태를 시작 상태로 기록 (persistence layer)

    // 1. from 멤버십 상태 확인 (membership Svc)
    MembershipStatus membershipStatus = membershipPort.getMembershipStatus(command.getFromMembershipId());
    if (!membershipStatus.isValid()) {
      // TODO Exception 처리
      return null;
    }

    // 2. 잔액 존재하는지 확인 (money svc)
    // 2-1. 잔액이 충분하지 않다면, 충전 요청 (money svc)

    // 3. 송금 타입 (고객/은행)
    //  3-1. 내부 고객일 경우
    //   from 고객 머니 감액, to 고객 머니 증액 (money svc)
    //  3-2. 외부 은행 계좌일 경우
    //   외부 은행 계좌가 적절한지 확인 (banking svc)
    //   법인계좌 -> 외부 은행 계좌 펌뱅킹 요청 (banking svc)

    // 4. 송금 요청 상태를 성공으로 기록 (persistence layer)

    return null;
  }
}
