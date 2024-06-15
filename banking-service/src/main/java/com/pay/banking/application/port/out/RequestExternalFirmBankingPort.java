package com.pay.banking.application.port.out;

import com.pay.banking.adapter.out.persistence.external.bank.ExternalFirmBankingRequest;
import com.pay.banking.adapter.out.persistence.external.bank.FirmBankingResult;

public interface RequestExternalFirmBankingPort {

  FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest request);

}
