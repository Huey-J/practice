package com.pay.banking.adapter.out.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pay.banking.application.port.out.GetMembershipPort;
import com.pay.banking.application.port.out.Membership;
import com.pay.common.CommonHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MembershipServiceAdapter implements GetMembershipPort {

  private final CommonHttpClient httpClient;
  private final String membershipServiceUrl;

  public MembershipServiceAdapter(CommonHttpClient httpClient,
      @Value("${service.membership.url}") String membershipServiceUrl) {
    this.httpClient = httpClient;
    this.membershipServiceUrl = membershipServiceUrl;
  }

  @Override
  public Membership getMembership(Long membershipId) {
    String url = String.join("/", membershipServiceUrl, "membership", membershipId.toString());

    try {
      ObjectMapper objectMapper = new ObjectMapper();

      String responseBody = httpClient.sendGetRequest(url).body();

      return objectMapper.readValue(responseBody, Membership.class);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
