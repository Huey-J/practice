package com.pay.remittance.application.port.out.membership;

public interface MembershipPort {

  MembershipStatus getMembershipStatus(Long membershipId);
}