package com.pay.remittance.adapter.out.service.membership;

import lombok.*;

@NoArgsConstructor
@Data
public class Membership {

  private Long membershipId;
  private String name;
  private String email;
  private String address;
  private boolean valid;
  private boolean corp;

  public Membership(Long membershipId, String name, String email, String address, boolean valid,
      boolean corp) {
    this.membershipId = membershipId;
    this.name = name;
    this.email = email;
    this.address = address;
    this.valid = valid;
    this.corp = corp;
  }

  @Override
  public String toString() {
    return "Membership from Remittance {" +
        "membershipId='" + membershipId + '\'' +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", address='" + address + '\'' +
        ", valid=" + valid +
        ", corp=" + corp +
        '}';
  }
}
