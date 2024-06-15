package com.pay.banking.adapter.out.persistence.external.bank;

import lombok.Data;

@Data
public class FirmBankingResult {

  private boolean success;

  public FirmBankingResult(boolean success) {
    this.success = success;
  }
}
