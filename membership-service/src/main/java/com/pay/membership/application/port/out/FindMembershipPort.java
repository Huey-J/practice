package com.pay.membership.application.port.out;

import com.pay.membership.adapter.out.persistence.MembershipJpaEntity;

public interface FindMembershipPort {

	MembershipJpaEntity findMembership(long membershipId);
}
