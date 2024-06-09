package com.pay.banking.domain;

import lombok.Getter;

@Getter
public class RegisteredBankAccount {

  private final Long registeredBankAccountId;
  private final Long membershipId;
  private final String bankName;
  private final String bankAccountNumber;
  private final boolean linkedStatus;

  public RegisteredBankAccount(Long registeredBankAccountId, Long membershipId, String bankName,
      String bankAccountNumber, boolean linkedStatus) {
    this.registeredBankAccountId = registeredBankAccountId;
    this.membershipId = membershipId;
    this.bankName = bankName;
    this.bankAccountNumber = bankAccountNumber;
    this.linkedStatus = linkedStatus;
  }
}