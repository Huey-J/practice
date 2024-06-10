package com.pay.banking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pay.banking.adapter.in.web.RegisterBankAccountRequest;
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
public class RegisterBankAccountControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @Test
  public void testRegisterBankAccount() throws Exception {
    RegisterBankAccountRequest request = new RegisterBankAccountRequest(
        1l, "은행이름", "123-00-123", true);
    mockMvc.perform(
            MockMvcRequestBuilders.post("/banking/register/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        )
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

}
