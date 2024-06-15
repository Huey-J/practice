package com.pay.banking.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "firm_banking_request")
@Data
@NoArgsConstructor
public class FirmBankingRequestJpaEntity {

  @Id
  @GeneratedValue
  private Long firmBankingRequestId;
  private String fromBankName;
  private String fromBankAccountNumber;
  private String toBankName;
  private String toBankAccountNumber;
  private int amount;
  private int firmBankingStatus;  // 0:요청, 1:완료, 2:거절
  private UUID uuid;

  public FirmBankingRequestJpaEntity(String fromBankName, String fromBankAccountNumber,
      String toBankName, String toBankAccountNumber, int amount, int firmBankingStatus, UUID uuid) {
    this.fromBankName = fromBankName;
    this.fromBankAccountNumber = fromBankAccountNumber;
    this.toBankName = toBankName;
    this.toBankAccountNumber = toBankAccountNumber;
    this.amount = amount;
    this.firmBankingStatus = firmBankingStatus;
    this.uuid = uuid;
  }

  @Override
  public String toString() {
    return "FirmBankingRequestJpaEntity{" +
        "firmBankingRequestId=" + firmBankingRequestId +
        ", fromBankName='" + fromBankName + '\'' +
        ", fromBankAccountNumber='" + fromBankAccountNumber + '\'' +
        ", toBankName='" + toBankName + '\'' +
        ", toBankAccountNumber='" + toBankAccountNumber + '\'' +
        ", amount=" + amount +
        ", firmBankingStatus=" + firmBankingStatus +
        ", uuid=" + uuid +
        '}';
  }
}
