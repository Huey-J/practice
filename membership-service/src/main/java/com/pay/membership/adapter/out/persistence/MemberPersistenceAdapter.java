package com.pay.membership.adapter.out.persistence;

import com.pay.membership.application.port.out.RegisterMembershipPort;
import common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements RegisterMembershipPort {

  private final SpringDataMembershipRepository membershipRepository;

  @Override
  public MembershipJpaEntity createMembership(String name, String address, String email, boolean isValid,
      boolean isCorp) {
    return membershipRepository.save(
        new MembershipJpaEntity(name, address, email, isValid, isCorp));
  }
}
