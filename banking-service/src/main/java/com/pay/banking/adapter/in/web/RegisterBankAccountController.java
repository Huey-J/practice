package com.pay.banking.adapter.in.web;

import com.pay.banking.application.port.in.RegisterBankAccountCommand;
import com.pay.banking.application.port.in.RegisterBankAccountUseCase;
import com.pay.banking.domain.RegisteredBankAccount;
import com.pay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

  private final RegisterBankAccountUseCase registerBankAccountUseCase;

  @PostMapping("/banking/register/account")
  RegisteredBankAccount registerBankAccount(@RequestBody RegisterBankAccountRequest request) {
    RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
        .membershipId(request.getMembershipId())
        .bankName(request.getBankName())
        .bankAccountNumber(request.getBankAccountNumber())
        .linkedStatus(request.isLinkedStatus())
        .build();

    return registerBankAccountUseCase.registerBankAccount(command);
  }
}
