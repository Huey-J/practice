package com.pay.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyChangingResultDetail {

  private Long moneyChangingRequestId;
  private int moneyChangingType;            // 0: 증액, 1: 감액
  private int moneyChangingResultStatus;    // enum. 0:요청, 1: 성공, 2: 실패
  private int amount;

}