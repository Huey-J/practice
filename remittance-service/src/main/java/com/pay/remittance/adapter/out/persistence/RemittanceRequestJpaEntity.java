package com.pay.remittance.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "request_remittance")
@Data
@NoArgsConstructor
public class RemittanceRequestJpaEntity {

  @Id
  @GeneratedValue
  private Long remittanceRequestId;
  private Long fromMembershipId; // from membership
  private Long toMembershipId; // to membership
  private String toBankName;
  private String toBankAccountNumber;
  private int remittanceType; // 0: membership(내부 고객), 1: bank (외부 은행 계좌)
  // 송금요청 금액
  private int amount;
  private String remittanceStatus;

  @Builder
  public RemittanceRequestJpaEntity(Long remittanceRequestId, Long fromMembershipId,
      Long toMembershipId, String toBankName, String toBankAccountNumber, int remittanceType,
      int amount, String remittanceStatus) {
    this.remittanceRequestId = remittanceRequestId;
    this.fromMembershipId = fromMembershipId;
    this.toMembershipId = toMembershipId;
    this.toBankName = toBankName;
    this.toBankAccountNumber = toBankAccountNumber;
    this.remittanceType = remittanceType;
    this.amount = amount;
    this.remittanceStatus = remittanceStatus;
  }
}
