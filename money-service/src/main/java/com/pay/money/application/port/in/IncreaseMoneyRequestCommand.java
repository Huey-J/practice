package com.pay.money.application.port.in;

import com.pay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class IncreaseMoneyRequestCommand extends SelfValidating<IncreaseMoneyRequestCommand> {

  @NotNull
  private final Long targetMembershipId;
  @NotNull
  private final int amount;

  public IncreaseMoneyRequestCommand(Long targetMembershipId, int amount) {
    this.targetMembershipId = targetMembershipId;
    this.amount = amount;
    this.validateSelf();
  }

}
