package com.pay.banking.domain;

import java.util.UUID;
import lombok.Getter;

@Getter
public class FirmBankingRequest {

  private Long firmBankingRequestId;
  private String fromBankName;
  private String fromBankAccountNumber;
  private String toBankName;
  private String toBankAccountNumber;
  private int amount;
  private int firmbankingStatus;  // 0:요청, 1:완료, 2:거절
  private UUID uuid;

  public FirmBankingRequest(Long firmBankingRequestId, String fromBankName,
      String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int amount,
      int firmbankingStatus, UUID uuid) {
    this.firmBankingRequestId = firmBankingRequestId;
    this.fromBankName = fromBankName;
    this.fromBankAccountNumber = fromBankAccountNumber;
    this.toBankName = toBankName;
    this.toBankAccountNumber = toBankAccountNumber;
    this.amount = amount;
    this.firmbankingStatus = firmbankingStatus;
    this.uuid = uuid;
  }
}
