package com.pay.membership.application.service;

import com.pay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.pay.membership.adapter.out.persistence.MembershipMapper;
import com.pay.membership.application.port.in.RegisterMembershipCommand;
import com.pay.membership.application.port.in.RegisterMembershipUseCase;
import com.pay.membership.application.port.out.RegisterMembershipPort;
import com.pay.membership.domain.Membership;
import common.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterMembershipService implements RegisterMembershipUseCase {

  private final RegisterMembershipPort registerMembershipPort;
  private final MembershipMapper membershipMapper;

  @Override
  public Membership registerMembership(RegisterMembershipCommand command) {
    MembershipJpaEntity membershipJpaEntity = registerMembershipPort.createMembership(
        command.getName(),
        command.getAddress(),
        command.getEmail(),
        command.isValid(),
        command.isCorp());

    return membershipMapper.mapToDomainEntity(membershipJpaEntity);
  }
}
