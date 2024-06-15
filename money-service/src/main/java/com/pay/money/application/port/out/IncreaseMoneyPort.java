package com.pay.money.application.port.out;

import com.pay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.pay.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;

public interface IncreaseMoneyPort {

  MoneyChangingRequestJpaEntity createMoneyChangingRequest(Long targetMembershipId,
      int moneyChangingType, int amount, int changingMoneyStatus);

  MemberMoneyJpaEntity increaseMoney(Long memberId, int increaseMoneyAmount);
}
