package com.pay.money.domain;

import lombok.Getter;

@Getter
public class MemberMoney {

  private final Long memberMoneyId;
  private final Long membershipId;
  private final int balance;
  // private final int linkedBankAccount;

  public MemberMoney(Long memberMoneyId, Long membershipId, int balance) {
    this.memberMoneyId = memberMoneyId;
    this.membershipId = membershipId;
    this.balance = balance;
  }
}
