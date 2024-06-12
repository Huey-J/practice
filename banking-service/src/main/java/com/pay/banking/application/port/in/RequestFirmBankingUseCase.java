package com.pay.banking.application.port.in;

import com.pay.banking.domain.FirmBankingRequest;

public interface RequestFirmBankingUseCase {

  FirmBankingRequest requestFirmBanking(RequestFirmBankingCommand command);

}
