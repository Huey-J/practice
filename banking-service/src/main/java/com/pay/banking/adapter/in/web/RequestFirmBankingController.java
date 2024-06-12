package com.pay.banking.adapter.in.web;

import com.pay.banking.application.port.in.RequestFirmBankingCommand;
import com.pay.banking.application.port.in.RequestFirmBankingUseCase;
import com.pay.banking.domain.FirmBankingRequest;
import com.pay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestFirmBankingController {

  private final RequestFirmBankingUseCase requestFirmBankingUseCase;

  @PostMapping("/banking/firmbanking/request")
  FirmBankingRequest registerBankAccount(@RequestBody RequestFirmBankingRequest request) {
    RequestFirmBankingCommand command = RequestFirmBankingCommand.builder()
        .fromBankName(request.getFromBankName())
        .fromBankAccountNumber(request.getFromBankAccountNumber())
        .toBankName(request.getToBankName())
        .toBankAccountNumber(request.getToBankAccountNumber())
        .amount(request.getAmount())
        .build();

    return requestFirmBankingUseCase.requestFirmBanking(command);
  }
}
