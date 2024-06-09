package com.pay.banking.application.port.in;

import com.pay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterBankAccountCommand extends SelfValidating<RegisterBankAccountCommand> {

  @NotNull
  private final Long membershipId;
  @NotNull
  private final String bankName;
  @NotNull
  private final String bankAccountNumber;
  private final boolean linkedStatus;

  public RegisterBankAccountCommand(Long membershipId, String bankName, String bankAccountNumber,
      boolean linkedStatus) {
    this.membershipId = membershipId;
    this.bankName = bankName;
    this.bankAccountNumber = bankAccountNumber;
    this.linkedStatus = linkedStatus;

    this.validateSelf();
  }
}
