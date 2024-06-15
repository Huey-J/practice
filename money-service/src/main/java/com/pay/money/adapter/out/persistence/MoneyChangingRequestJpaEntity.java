package com.pay.money.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "money_changing_request")
@Data
@NoArgsConstructor
public class MoneyChangingRequestJpaEntity {

  @Id
  @GeneratedValue
  private Long moneyChangingRequestId;
  private Long targetMembershipId;
  private int moneyChangingType; // 0: 증액, 1: 감액
  private int amount;
  @Temporal(TemporalType.TIMESTAMP)
  private Date timestamp;

  private int changingMoneyStatus; // 0: 요청, 1: 성공, 2: 실패

  private String uuid;

  public MoneyChangingRequestJpaEntity(Long targetMembershipId, int moneyChangingType, int amount,
      Timestamp timestamp, int changingMoneyStatus, String uuid) {
    this.targetMembershipId = targetMembershipId;
    this.moneyChangingType = moneyChangingType;
    this.amount = amount;
    this.timestamp = timestamp;
    this.changingMoneyStatus = changingMoneyStatus;
    this.uuid = uuid;
  }
}
