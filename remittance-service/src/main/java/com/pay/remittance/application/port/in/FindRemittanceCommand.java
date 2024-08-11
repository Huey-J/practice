package com.pay.remittance.application.port.in;

import com.pay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FindRemittanceCommand extends SelfValidating<FindRemittanceCommand> {

  @NotNull
  private Long membershipId;

  public FindRemittanceCommand(Long membershipId) {
    this.membershipId = membershipId;
    this.validateSelf();
  }
}
