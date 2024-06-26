package com.pay.money.adapter.in.web;

import com.pay.money.domain.MoneyChangingRequest;
import org.springframework.stereotype.Component;

@Component
public class MoneyChangingResultDetailMapper {

  public MoneyChangingResultDetail mapToMoneyChangingResultDetail(
      MoneyChangingRequest moneyChangingRequest) {
    return new MoneyChangingResultDetail(
        moneyChangingRequest.getMoneyChangingRequestId(),
        moneyChangingRequest.getChangingType(),
        moneyChangingRequest.getChangingMoneyStatus(),
        moneyChangingRequest.getAmount()
    );
  }
}
