package com.pay.membership.domain;

import lombok.Getter;

@Getter
public class Membership {

  private final String membershipId;
  private final String name;
  private final String email;
  private final String address;
  private final boolean isValid;
  private final boolean isCorp;

  public Membership(String membershipId, String name, String email, String address,
      boolean isValid, boolean isCorp) {
    this.membershipId = membershipId;
    this.name = name;
    this.email = email;
    this.address = address;
    this.isValid = isValid;
    this.isCorp = isCorp;
  }
}