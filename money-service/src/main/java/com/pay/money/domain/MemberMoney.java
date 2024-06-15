package com.pay.money.domain;

import lombok.Getter;

@Getter
public class MemberMoney {

  private final String memberMoneyId;
  private final String membershipId;
  private final int balance;
  // private final int linkedBankAccount;

  public MemberMoney(String memberMoneyId, String membershipId, int balance) {
    this.memberMoneyId = memberMoneyId;
    this.membershipId = membershipId;
    this.balance = balance;
  }
}
