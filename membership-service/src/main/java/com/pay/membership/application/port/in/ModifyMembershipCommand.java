package com.pay.membership.application.port.in;

import com.pay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class ModifyMembershipCommand extends SelfValidating<ModifyMembershipCommand> {

  @NotNull
  private final Long membershipId;
  @NotNull
  private final String name;
  @NotNull
  private String address;
  @NotNull
  private String email;
  private boolean isValid;
  private boolean isCorp;

  public ModifyMembershipCommand(Long membershipId, String name, String address, String email,
      boolean isValid, boolean isCorp) {
    this.membershipId = membershipId;
    this.name = name;
    this.address = address;
    this.email = email;
    this.isValid = isValid;
    this.isCorp = isCorp;

    this.validateSelf();
  }
}
