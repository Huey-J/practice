package com.pay.banking.adapter.out.persistence;

import com.pay.banking.application.port.out.RegisterBankAccountPort;
import com.pay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort {

  private final SpringDataRegisteredBankAccountRepository bankAccountRepository;

  @Override
  public RegisteredBankAccountJpaEntity createRegisteredBankAccount(Long membershipId,
      String bankName, String bankAccountNumber, boolean linkedStatus) {
    return bankAccountRepository.save(
        new RegisteredBankAccountJpaEntity(membershipId, bankName, bankAccountNumber,
            linkedStatus));
  }
}
