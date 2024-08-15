package com.pay.money.application.service;

import com.pay.common.UseCase;
import com.pay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.pay.money.adapter.out.persistence.MemberMoneyMapper;
import com.pay.money.application.port.in.FindMemberMoneyUseCase;
import com.pay.money.application.port.out.FindMemberMoneyPort;
import com.pay.money.domain.MemberMoney;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
@Transactional
public class FindMemberMoneyService implements FindMemberMoneyUseCase {

  private final FindMemberMoneyPort findMemberMoneyPort;
  private final MemberMoneyMapper memberMoneyMapper;

  @Override
  public MemberMoney findMemberMoney(Long membershipId) {
    MemberMoneyJpaEntity memberMoneyJpaEntity = findMemberMoneyPort
        .getByMembershipId(membershipId);
    return memberMoneyMapper.mapToDomainEntity(memberMoneyJpaEntity);
  }

}
