package com.pay.remittance.domain;

import lombok.Getter;

@Getter
public class RemittanceRequest {

  private final Long remittanceRequestId;
  private final Long remittanceFromMembershipId;
  private final String toBankName;
  private final String toBankAccountNumber;
  private int remittanceType; // 0: membership(내부 고객), 1: bank (외부 은행 계좌)
  // 송금요청 금액
  private int amount;
  private String remittanceStatus;

  public RemittanceRequest(Long remittanceRequestId, Long remittanceFromMembershipId,
      String toBankName, String toBankAccountNumber, int remittanceType, int amount,
      String remittanceStatus) {
    this.remittanceRequestId = remittanceRequestId;
    this.remittanceFromMembershipId = remittanceFromMembershipId;
    this.toBankName = toBankName;
    this.toBankAccountNumber = toBankAccountNumber;
    this.remittanceType = remittanceType;
    this.amount = amount;
    this.remittanceStatus = remittanceStatus;
  }
}
