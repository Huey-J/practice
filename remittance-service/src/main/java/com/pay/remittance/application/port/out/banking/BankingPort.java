package com.pay.remittance.application.port.out.banking;

public interface BankingPort {

  boolean requestFirmbanking(String bankName, String bankAccountNumber, int amount);
}
