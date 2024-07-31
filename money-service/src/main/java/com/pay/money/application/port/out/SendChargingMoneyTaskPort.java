package com.pay.money.application.port.out;

import com.pay.common.ChargingMoneyTask;

public interface SendChargingMoneyTaskPort {

  void sendChargingMoneyTask(ChargingMoneyTask task);

}
