package com.pay.money.adapter.in.web;

import com.pay.common.WebAdapter;
import com.pay.money.application.port.in.FindMemberMoneyUseCase;
import com.pay.money.domain.MemberMoney;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindMemberMoneyController {

  private final FindMemberMoneyUseCase findMemberMoneyUseCase;

  @GetMapping("/money/membership/{membershipId}")
  MemberMoney findMoneyInfo(@PathVariable Long membershipId) {
    return findMemberMoneyUseCase.findMemberMoney(membershipId);
  }

}
