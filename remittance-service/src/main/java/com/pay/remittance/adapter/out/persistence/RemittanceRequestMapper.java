package com.pay.remittance.adapter.out.persistence;

import com.pay.remittance.domain.RemittanceRequest;
import org.springframework.stereotype.Component;

@Component
public class RemittanceRequestMapper {

  public RemittanceRequest mapToDomainEntity(
      RemittanceRequestJpaEntity remittanceRequestJpaEntity) {
    return new RemittanceRequest(
        remittanceRequestJpaEntity.getRemittanceRequestId(),
        remittanceRequestJpaEntity.getFromMembershipId(),
        remittanceRequestJpaEntity.getToBankName(),
        remittanceRequestJpaEntity.getToBankAccountNumber(),
        remittanceRequestJpaEntity.getRemittanceType(),
        remittanceRequestJpaEntity.getAmount(),
        remittanceRequestJpaEntity.getRemittanceStatus()
    );
  }
}
