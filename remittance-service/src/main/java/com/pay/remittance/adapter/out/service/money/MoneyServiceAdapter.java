package com.pay.remittance.adapter.out.service.money;

import com.pay.common.CommonHttpClient;
import com.pay.common.ExternalSystemAdapter;
import com.pay.remittance.application.port.out.money.MoneyInfo;
import com.pay.remittance.application.port.out.money.MoneyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MoneyServiceAdapter implements MoneyPort {

  private final CommonHttpClient moneyServiceHttpClient;

  @Value("${service.money.url}")
  private String moneyServiceEndpoint;

  @Override
  public MoneyInfo getMoneyInfo(Long membershipId) {
    return null;
  }

  @Override
  public boolean requestMoneyRecharging(Long membershipId, int amount) {
    return false;
  }

  @Override
  public boolean requestMoneyIncrease(Long membershipId, int amount) {
    return false;
  }

  @Override
  public boolean requestMoneyDecrease(Long membershipId, int amount) {
    return false;
  }
}
