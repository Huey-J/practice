package com.pay.remittance.application.port.in;

import com.pay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RequestRemittanceCommand extends SelfValidating<RequestRemittanceCommand> {

  @NotNull
  private Long fromMembershipId;
  private Long toMembershipId;

  private String toBankName;
  private String toBankAccountNumber;

  private int remittanceType; // 0: membership(내부 고객), 1: bank (외부 은행 계좌)

  @NotNull
  private int amount;   // 송금요청 금액

  public RequestRemittanceCommand(Long fromMembershipId, Long toMembershipId, String toBankName,
      String toBankAccountNumber, int remittanceType, int amount) {
    this.fromMembershipId = fromMembershipId;
    this.toMembershipId = toMembershipId;
    this.toBankName = toBankName;
    this.toBankAccountNumber = toBankAccountNumber;
    this.remittanceType = remittanceType;
    this.amount = amount;

    this.validateSelf();
  }
}
