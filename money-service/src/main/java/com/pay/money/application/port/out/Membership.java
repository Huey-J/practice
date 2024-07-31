package com.pay.money.application.port.out;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Membership {

  private String membershipId;
  private String name;
  private String email;
  private String address;
  private boolean valid;
  private boolean corp;
//  private boolean isValid;
//  private boolean isCorp;

  public Membership(String membershipId, String name, String email, String address,
      boolean valid, boolean corp) {
    this.membershipId = membershipId;
    this.name = name;
    this.email = email;
    this.address = address;
    this.valid = valid;
    this.corp = corp;
//    this.isValid = isValid;
//    this.isCorp = isCorp;
  }
}