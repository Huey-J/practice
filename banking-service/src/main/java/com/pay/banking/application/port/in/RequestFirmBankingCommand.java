package com.pay.banking.application.port.in;

import com.pay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RequestFirmBankingCommand extends SelfValidating<RequestFirmBankingCommand> {

  @NotNull
  private String fromBankName;
  @NotNull
  private String fromBankAccountNumber;
  @NotNull
  private String toBankName;
  @NotNull
  private String toBankAccountNumber;
  private int amount;

  public RequestFirmBankingCommand(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int amount) {
    this.fromBankName = fromBankName;
    this.fromBankAccountNumber = fromBankAccountNumber;
    this.toBankName = toBankName;
    this.toBankAccountNumber = toBankAccountNumber;
    this.amount = amount;
    this.validateSelf();
  }


}
