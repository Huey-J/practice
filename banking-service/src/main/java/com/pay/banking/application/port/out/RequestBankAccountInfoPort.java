package com.pay.banking.application.port.out;

import com.pay.banking.adapter.out.persistence.external.bank.GetBankAccountRequest;
import com.pay.banking.domain.BankAccount;

public interface RequestBankAccountInfoPort {

  BankAccount getBankAccountInfo(GetBankAccountRequest request);

}
