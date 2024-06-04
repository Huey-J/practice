package com.pay.membership.application.service;

import com.pay.common.UseCase;
import com.pay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.pay.membership.adapter.out.persistence.MembershipMapper;
import com.pay.membership.application.port.in.FindMembershipCommand;
import com.pay.membership.application.port.in.FindMembershipUseCase;
import com.pay.membership.application.port.out.FindMembershipPort;
import com.pay.membership.domain.Membership;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
@Transactional
public class FindMembershipService implements FindMembershipUseCase {

	private final FindMembershipPort findMembershipPort;
	private final MembershipMapper membershipMapper;

	@Override
	public Membership findMembershipByMemberId(FindMembershipCommand command) {
		MembershipJpaEntity membershipJpaEntity = findMembershipPort.findMembership(
				command.getMembershipId());
		return membershipMapper.mapToDomainEntity(membershipJpaEntity);
	}
}