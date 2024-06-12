package com.pay.banking.adapter.out.persistence;

import com.pay.banking.domain.FirmBankingRequest;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class FirmBankingRequestMapper {

  public FirmBankingRequest mapToDomainEntity(
      FirmBankingRequestJpaEntity firmBankingRequestJpaEntity, UUID uuid) {
    return new FirmBankingRequest(
        firmBankingRequestJpaEntity.getFirmBankingRequestId(),
        firmBankingRequestJpaEntity.getFromBankName(),
        firmBankingRequestJpaEntity.getFromBankAccountNumber(),
        firmBankingRequestJpaEntity.getToBankName(),
        firmBankingRequestJpaEntity.getToBankAccountNumber(),
        firmBankingRequestJpaEntity.getAmount(),
        firmBankingRequestJpaEntity.getFirmBankingStatus(),
        uuid
    );
  }

}
