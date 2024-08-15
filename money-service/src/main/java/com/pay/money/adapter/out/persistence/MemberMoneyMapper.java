package com.pay.money.adapter.out.persistence;

import com.pay.money.domain.MemberMoney;
import org.springframework.stereotype.Component;

@Component
public class MemberMoneyMapper {

  public MemberMoney mapToDomainEntity(MemberMoneyJpaEntity memberMoneyJpaEntity) {
    return new MemberMoney(
        memberMoneyJpaEntity.getMemberMoneyId(),
        memberMoneyJpaEntity.getMembershipId(),
        memberMoneyJpaEntity.getBalance()
    );
  }

}
