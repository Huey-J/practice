package com.pay.membership.application.service;

import com.pay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.pay.membership.adapter.out.persistence.MembershipMapper;
import com.pay.membership.application.port.in.ModifyMembershipCommand;
import com.pay.membership.application.port.in.ModifyMembershipUseCase;
import com.pay.membership.application.port.out.ModifyMembershipPort;
import com.pay.membership.domain.Membership;
import common.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ModifyMembershipService implements ModifyMembershipUseCase {

  private final ModifyMembershipPort modifyMembershipPort;
  private final MembershipMapper membershipMapper;

  @Override
  public Membership modifyMembership(ModifyMembershipCommand command) {
    MembershipJpaEntity membershipJpaEntity = modifyMembershipPort.modifyMembership(
        command.getMembershipId(),
        command.getName(),
        command.getAddress(),
        command.getEmail(),
        command.isValid(),
        command.isCorp());

    return membershipMapper.mapToDomainEntity(membershipJpaEntity);
  }

}
