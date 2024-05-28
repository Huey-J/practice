package com.pay.membership.adapter.out.persistence;

import com.pay.membership.domain.Membership;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {

  public Membership mapToDomainEntity(MembershipJpaEntity membershipJpaEntity) {
    return new Membership(
        membershipJpaEntity.getMemebershipId().toString(),
        membershipJpaEntity.getName(),
        membershipJpaEntity.getEmail(),
        membershipJpaEntity.getAddress(),
        membershipJpaEntity.isValid(),
        membershipJpaEntity.isCorp());
  }

}
