package com.pay.remittance.adapter.out.persistence;

import com.pay.common.PersistenceAdapter;
import com.pay.remittance.application.port.RequestRemittancePort;
import com.pay.remittance.application.port.in.RequestRemittanceCommand;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RemittanceRequestPersistenceAdapter implements RequestRemittancePort {

  private final SpringDataRemittanceRequestRepository remittanceRequestRepository;

  @Override
  public RemittanceRequestJpaEntity createRemittanceRequestHistory(
      RequestRemittanceCommand command) {
    return remittanceRequestRepository.save(RemittanceRequestJpaEntity.builder()
        .fromMembershipId(command.getFromMembershipId())
        .toMembershipId(command.getToMembershipId())
        .toBankName(command.getToBankName())
        .toBankAccountNumber(command.getToBankAccountNumber())
        .amount(command.getAmount())
        .remittanceType(command.getRemittanceType())
        .build());
  }

  @Override
  public boolean saveRemittanceRequestHistory(RemittanceRequestJpaEntity entity) {
    remittanceRequestRepository.save(entity);
    return true;
  }

}
