package com.pay.remittance.application.port.out.money;

public interface MoneyPort {

  MoneyInfo getMoneyInfo(Long membershipId);

  boolean requestMoneyRecharging(Long membershipId, int amount);

  boolean requestMoneyIncrease(Long membershipId, int amount);

  boolean requestMoneyDecrease(Long membershipId, int amount);
}
