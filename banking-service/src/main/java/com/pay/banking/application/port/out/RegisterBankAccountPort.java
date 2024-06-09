package com.pay.banking.application.port.out;

import com.pay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;

public interface RegisterBankAccountPort {

  RegisteredBankAccountJpaEntity createRegisteredBankAccount(
      Long membershipId, String bankName, String bankAccountNumber, boolean linkedStatus);

}
