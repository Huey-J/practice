package com.pay.banking.application.service;

import com.pay.banking.application.port.in.RequestFirmBankingCommand;
import com.pay.banking.application.port.in.RequestFirmBankingUseCase;
import com.pay.banking.domain.FirmBankingRequest;
import com.pay.common.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmBankingService implements RequestFirmBankingUseCase {

  @Override
  public FirmBankingRequest requestFirmBanking(RequestFirmBankingCommand command) {
    // TODO
    return null;
  }
}
