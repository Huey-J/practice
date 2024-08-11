package com.pay.remittance.domain;

import lombok.Getter;

@Getter
public class RemittanceRequest {

  private final String remittanceRequestId;

  public RemittanceRequest(String remittanceRequestId) {
    this.remittanceRequestId = remittanceRequestId;
  }
}
