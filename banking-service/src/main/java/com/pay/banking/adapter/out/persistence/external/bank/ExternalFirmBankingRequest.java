package com.pay.banking.adapter.out.persistence.external.bank;

import lombok.Data;

@Data
public class ExternalFirmBankingRequest {

  private String fromBankName;
  private String fromBankAccountNumber;
  private String toBankName;
  private String toBankAccountNumber;
  private int amount;

  public ExternalFirmBankingRequest(String fromBankName, String fromBankAccountNumber,
      String toBankName, String toBankAccountNumber, int amount) {
    this.fromBankName = fromBankName;
    this.fromBankAccountNumber = fromBankAccountNumber;
    this.toBankName = toBankName;
    this.toBankAccountNumber = toBankAccountNumber;
    this.amount = amount;
  }
}
