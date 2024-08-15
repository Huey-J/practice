package com.pay.money.adapter.out.persistence;

import com.pay.common.PersistenceAdapter;
import com.pay.money.application.port.out.FindMemberMoneyPort;
import com.pay.money.application.port.out.IncreaseMoneyPort;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyPort,
    FindMemberMoneyPort {

  private final SpringDataMoneyChangingRequestRepository moneyChangingRequestRepository;
  private final SpringDataMemberMoneyRepository memberMoneyRepository;

  @Override
  public MoneyChangingRequestJpaEntity createMoneyChangingRequest(Long targetMembershipId,
      int moneyChangingType, int amount, int changingMoneyStatus) {
    return moneyChangingRequestRepository.save(new MoneyChangingRequestJpaEntity(
        targetMembershipId,
        moneyChangingType,
        amount,
        new Timestamp(System.currentTimeMillis()),
        changingMoneyStatus,
        UUID.randomUUID().toString()
    ));
  }

  @Override
  public MemberMoneyJpaEntity increaseMoney(Long memberId, int increaseMoneyAmount) {
    MemberMoneyJpaEntity entity;
    try {
      List<MemberMoneyJpaEntity> entityList = memberMoneyRepository.findByMembershipId(memberId);
      entity = entityList.get(0);

      entity.setBalance(entity.getBalance() + increaseMoneyAmount);
      return memberMoneyRepository.save(entity);
    } catch (Exception e) {
      entity = new MemberMoneyJpaEntity(memberId, increaseMoneyAmount);
      return memberMoneyRepository.save(entity);
    }
  }

  @Override
  public MemberMoneyJpaEntity getByMembershipId(long membershipId) {
    return memberMoneyRepository.findByMembershipId(membershipId).get(0);
  }
}
