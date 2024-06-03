package com.pay.membership.application.port.out;

import com.pay.membership.adapter.out.persistence.MembershipJpaEntity;

public interface ModifyMembershipPort {

  MembershipJpaEntity modifyMembership(Long membershipId, String name, String address,
      String email, boolean isValid, boolean isCorp);

}
