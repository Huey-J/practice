package com.pay.remittance.application.port.out.membership;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipStatus {

  private Long membershipId;
  private boolean isValid;
}
