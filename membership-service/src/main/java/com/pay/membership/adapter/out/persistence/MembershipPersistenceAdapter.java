package com.pay.membership.adapter.out.persistence;

import com.pay.membership.application.port.out.FindMembershipPort;
import com.pay.membership.application.port.out.ModifyMembershipPort;
import com.pay.membership.application.port.out.RegisterMembershipPort;
import common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort,
    ModifyMembershipPort {

  private final SpringDataMembershipRepository membershipRepository;

  @Override
  public MembershipJpaEntity createMembership(String name, String address, String email, boolean isValid,
      boolean isCorp) {
    return membershipRepository.save(
        new MembershipJpaEntity(name, address, email, isValid, isCorp));
  }

  @Override
  public MembershipJpaEntity findMembership(long membershipId) {
    return membershipRepository.getById(membershipId);
  }

  @Override
  public MembershipJpaEntity modifyMembership(Long membershipId, String name, String address,
      String email, boolean isValid, boolean isCorp) {
    MembershipJpaEntity entity = membershipRepository.getById(membershipId);
    entity.setName(name);
    entity.setAddress(address);
    entity.setEmail(email);
    entity.setValid(isValid);
    entity.setCorp(isCorp);

    return membershipRepository.save(entity);
  }
}
