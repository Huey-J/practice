package com.pay.banking.domain;

import lombok.Getter;

@Getter
public class BankAccount {

  private final String bankName;
  private final String bankAccountNumber;
  private final boolean isValid;

  public BankAccount(String bankName, String bankAccountNumber, boolean isValid) {
    this.bankName = bankName;
    this.bankAccountNumber = bankAccountNumber;
    this.isValid = isValid;
  }
}
