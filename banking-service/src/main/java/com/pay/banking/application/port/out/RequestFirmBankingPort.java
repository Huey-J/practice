package com.pay.banking.application.port.out;

import com.pay.banking.adapter.out.persistence.FirmBankingRequestJpaEntity;

public interface RequestFirmBankingPort {

  FirmBankingRequestJpaEntity createFirmBankingRequest(String fromBankName, String fromBankAccountNumber,
      String toBankName, String toBankAccountNumber, int amount);

  FirmBankingRequestJpaEntity modifyFirmBankingRequest(FirmBankingRequestJpaEntity entity);

}
