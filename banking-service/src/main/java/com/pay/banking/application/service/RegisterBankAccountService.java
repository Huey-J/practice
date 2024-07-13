package com.pay.banking.application.service;

import com.pay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.pay.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.pay.banking.adapter.out.persistence.external.bank.GetBankAccountRequest;
import com.pay.banking.application.port.out.Membership;
import com.pay.banking.application.port.in.RegisterBankAccountCommand;
import com.pay.banking.application.port.in.RegisterBankAccountUseCase;
import com.pay.banking.application.port.out.GetMembershipPort;
import com.pay.banking.application.port.out.RegisterBankAccountPort;
import com.pay.banking.application.port.out.RequestBankAccountInfoPort;
import com.pay.banking.domain.BankAccount;
import com.pay.banking.domain.RegisteredBankAccount;
import com.pay.common.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

  private final RegisterBankAccountPort registerBankAccountPort;
  private final RequestBankAccountInfoPort requestBankAccountInfoPort;
  private final RegisteredBankAccountMapper registeredBankAccountMapper;
  private final GetMembershipPort getMembershipPort;

  @Override
  public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
    // 멤버 조회
    Membership membership = getMembershipPort.getMembership(command.getMembershipId());

    if (!membership.isValid()) {
      // TODO Exception 처리
      return null;
    }

    // 등록이 가능한 계좌인지 확인
    BankAccount accountInfo = requestBankAccountInfoPort.getBankAccountInfo(
        new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));

    if (accountInfo.isValid()) {
      // 등록
      RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity = registerBankAccountPort.createRegisteredBankAccount(
          command.getMembershipId(), command.getBankName(), command.getBankAccountNumber(), command.isLinkedStatus());

      return registeredBankAccountMapper.mapToDomainEntity(registeredBankAccountJpaEntity);
    } else {
      // TODO Exception 처리
      return null;
    }
  }
}
