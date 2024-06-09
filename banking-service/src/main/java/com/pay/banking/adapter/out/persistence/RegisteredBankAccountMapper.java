package com.pay.banking.adapter.out.persistence;

import com.pay.banking.domain.RegisteredBankAccount;
import org.springframework.stereotype.Component;

@Component
public class RegisteredBankAccountMapper {

  public RegisteredBankAccount mapToDomainEntity(RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity) {
    return new RegisteredBankAccount(
        registeredBankAccountJpaEntity.getRegisteredBankAccountId(),
        registeredBankAccountJpaEntity.getMembershipId(),
        registeredBankAccountJpaEntity.getBankName(),
        registeredBankAccountJpaEntity.getBankAccountNumber(),
        registeredBankAccountJpaEntity.isLinkedStatus()
    );
  }

}
