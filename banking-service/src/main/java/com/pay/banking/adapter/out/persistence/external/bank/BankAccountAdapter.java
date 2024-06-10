package com.pay.banking.adapter.out.persistence.external.bank;

import com.pay.banking.application.port.out.RequestBankAccountInfoPort;
import com.pay.banking.domain.BankAccount;
import com.pay.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestBankAccountInfoPort {

  @Override
  public BankAccount getBankAccountInfo(GetBankAccountRequest request) {
    // TODO 실제 외부 은행 API 호출
    return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
  }
}
