package com.pay.money.adapter.out.persistence;

import com.pay.money.domain.MoneyChangingRequest;
import org.springframework.stereotype.Component;

@Component
public class MoneyChangingRequestMapper {

  public MoneyChangingRequest mapToDomainEntity(
      MoneyChangingRequestJpaEntity moneyChangingRequestJpaEntity) {
    return new MoneyChangingRequest(
        moneyChangingRequestJpaEntity.getMoneyChangingRequestId(),
        moneyChangingRequestJpaEntity.getTargetMembershipId(),
        moneyChangingRequestJpaEntity.getMoneyChangingType(),
        moneyChangingRequestJpaEntity.getAmount(),
        moneyChangingRequestJpaEntity.getChangingMoneyStatus(),
        moneyChangingRequestJpaEntity.getUuid(),
        moneyChangingRequestJpaEntity.getTimestamp()
    );
  }
}
