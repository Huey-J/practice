package com.pay.remittance.adapter.in.web;

import com.pay.common.WebAdapter;
import com.pay.remittance.domain.RemittanceRequest;
import com.pay.remittance.application.port.in.RequestRemittanceCommand;
import com.pay.remittance.application.port.in.RequestRemittanceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestRemittanceController {

  private final RequestRemittanceUseCase requestRemittanceUseCase;

  // 송금 요청
  @PostMapping(path = "/remittance/request")
  RemittanceRequest requestRemittance(@RequestBody RequestRemittanceRequest request) {
    RequestRemittanceCommand command = RequestRemittanceCommand.builder()
        .fromMembershipId(request.getFromMembershipId())
        .toMembershipId(request.getToMembershipId())
        .toBankName(request.getToBankName())
        .toBankAccountNumber(request.getToBankAccountNumber())
        .remittanceType(request.getRemittanceType())
        .amount(request.getAmount())
        .build();

    return requestRemittanceUseCase.requestRemittance(command);
  }
}
