package com.pay.banking.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registered_bank_account")
@Data
@NoArgsConstructor
public class RegisteredBankAccountJpaEntity {

  @Id
  @GeneratedValue
  private Long registeredBankAccountId;
  private Long membershipId;
  private String bankName;
  private String bankAccountNumber;
  private boolean linkedStatus;

  public RegisteredBankAccountJpaEntity(Long membershipId, String bankName,
      String bankAccountNumber, boolean linkedStatus) {
    this.membershipId = membershipId;
    this.bankName = bankName;
    this.bankAccountNumber = bankAccountNumber;
    this.linkedStatus = linkedStatus;
  }

  @Override
  public String toString() {
    return "RegisteredBankAccountJpaEntity{" +
        "registeredBankAccountId=" + registeredBankAccountId +
        ", membershipId=" + membershipId +
        ", bankName='" + bankName + '\'' +
        ", bankAccountNumber='" + bankAccountNumber + '\'' +
        ", linkedStatus=" + linkedStatus +
        '}';
  }
}
