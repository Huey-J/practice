package com.pay.money.adapter.out.persistence;

import com.pay.common.PersistenceAdapter;
import com.pay.money.application.port.out.IncreaseMoneyPort;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyPort {

  private final SpringDataMoneyChangingRequestRepository moneyChangingRequestRepository;

  @Override
  public MoneyChangingRequestJpaEntity createMoneyChangingRequest(Long targetMembershipId,
      int moneyChangingType, int amount, int changingMoneyStatus) {
    return moneyChangingRequestRepository.save(new MoneyChangingRequestJpaEntity(
        targetMembershipId,
        moneyChangingType,
        amount,
        new Timestamp(System.currentTimeMillis()),
        changingMoneyStatus,
        UUID.randomUUID().toString()
    ));
  }

}
