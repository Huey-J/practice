package com.pay.remittance.adapter.out.service.banking;

import com.pay.common.CommonHttpClient;
import com.pay.common.ExternalSystemAdapter;
import com.pay.remittance.application.port.out.banking.BankingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankingServiceAdapter implements BankingPort {

  private final CommonHttpClient bankingServiceHttpClient;

  @Value("${service.banking.url}")
  private String bankingServiceEndpoint;

  @Override
  public boolean requestFirmbanking(String bankName, String bankAccountNumber, int amount) {
    return false;
  }
}
