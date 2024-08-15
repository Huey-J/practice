package com.pay.money.application.port.out;

import com.pay.money.adapter.out.persistence.MemberMoneyJpaEntity;

public interface FindMemberMoneyPort {

  MemberMoneyJpaEntity getByMembershipId(long membershipId);

}
