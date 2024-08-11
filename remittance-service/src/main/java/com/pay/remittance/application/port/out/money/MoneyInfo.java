package com.pay.remittance.application.port.out.money;

import lombok.Data;

@Data
public class MoneyInfo {

  private Long membershipId;
  private int balance;
}
