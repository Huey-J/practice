package com.pay.membership;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pay.membership.adapter.in.web.RegisterMembershipRequest;
import com.pay.membership.domain.Membership;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterMembershipControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @Test
  public void testRegisterMembership() throws Exception {
    RegisterMembershipRequest request = new RegisterMembershipRequest(
        "name", "address", "email", false);
    Membership expect = new Membership(
        "1", "name", "email", "address", true, false);

    mockMvc.perform(
            MockMvcRequestBuilders.post("/membership/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(expect)));
  }

}
