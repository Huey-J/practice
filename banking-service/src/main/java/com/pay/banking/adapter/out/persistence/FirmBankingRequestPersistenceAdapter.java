package com.pay.banking.adapter.out.persistence;

import com.pay.banking.application.port.out.RequestFirmBankingPort;
import com.pay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class FirmBankingRequestPersistenceAdapter implements RequestFirmBankingPort {

  private final FirmBankingRequestRepository firmBankingRequestRepository;

  @Override
  public FirmBankingRequestJpaEntity createFirmBankingRequest(String fromBankName,
      String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int amount) {
    return firmBankingRequestRepository.save(new FirmBankingRequestJpaEntity(
        fromBankName,
        fromBankAccountNumber,
        toBankName,
        toBankAccountNumber,
        amount,
        0)
    );
  }
}
