package com.pay.membership.application.port.in;

import common.SelfValidating;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterMembershipCommand extends SelfValidating<RegisterMembershipCommand> {

  @NotNull
  private final String name;
  @NotNull
  private String address;
  @NotNull
  private String email;
  @AssertTrue
  private boolean isValid;
  private boolean isCorp;

  public RegisterMembershipCommand(String name, String address, String email, boolean isValid,
      boolean isCorp) {
    this.name = name;
    this.address = address;
    this.email = email;
    this.isValid = isValid;
    this.isCorp = isCorp;

    this.validateSelf();
  }
}
