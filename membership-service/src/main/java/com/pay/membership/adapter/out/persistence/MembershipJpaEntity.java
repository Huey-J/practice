package com.pay.membership.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "membership")
@Data
@NoArgsConstructor
public class MembershipJpaEntity {

  @Id
  @GeneratedValue
  private Long memebershipId;
  private String name;
  private String address;
  private String email;
  private boolean isValid;
  private boolean isCorp;

  public MembershipJpaEntity(String name, String address, String email, boolean isValid,
      boolean isCorp) {
    this.name = name;
    this.address = address;
    this.email = email;
    this.isValid = isValid;
    this.isCorp = isCorp;
  }

  @Override
  public String toString() {
    return "MembershipJpaEntity{" +
        "memebershipId=" + memebershipId +
        ", name='" + name + '\'' +
        ", address='" + address + '\'' +
        ", email='" + email + '\'' +
        ", isValid=" + isValid +
        ", isCorp=" + isCorp +
        '}';
  }
}
