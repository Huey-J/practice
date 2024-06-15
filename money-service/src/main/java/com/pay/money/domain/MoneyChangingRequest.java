package com.pay.money.domain;

import java.util.Date;
import lombok.Getter;

@Getter
public class MoneyChangingRequest {

  private final Long moneyChangingRequestId;
  private final Long targetMembershipId;  // 어떤 고객의 증액/감액 요청을 요청했는지의 멤버 정보
  private final int changingType; // enum. 0: 증액, 1: 감액
  private final int amount;
  private final int changingMoneyStatus; // enum. 0:요청, 1: 성공, 2: 실패

  private final String uuid;

  private final Date createdAt;

  public MoneyChangingRequest(Long moneyChangingRequestId, Long targetMembershipId,
      int changingType, int amount, int changingMoneyStatus, String uuid, Date createdAt) {
    this.moneyChangingRequestId = moneyChangingRequestId;
    this.targetMembershipId = targetMembershipId;
    this.changingType = changingType;
    this.amount = amount;
    this.changingMoneyStatus = changingMoneyStatus;
    this.uuid = uuid;
    this.createdAt = createdAt;
  }
}
