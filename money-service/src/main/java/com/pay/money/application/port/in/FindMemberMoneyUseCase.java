package com.pay.money.application.port.in;

import com.pay.money.domain.MemberMoney;

public interface FindMemberMoneyUseCase {

  MemberMoney findMemberMoney(Long membershipId);

}
