package com.pay.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChargingMoneyTask { // Increase Money

    private String taskID;
    private String taskName;

    private Long membershipID;

    private List<SubTask> subTaskList; // sub task list

    // 법인 계좌
    private String toBankName;

    // 법인 계좌 번호
    private String toBankAccountNumber;

    private int moneyAmount; // only won
}
