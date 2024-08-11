package com.pay.remittance.application.service;

import com.pay.common.UseCase;
import com.pay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.pay.remittance.adapter.out.persistence.RemittanceRequestMapper;
import com.pay.remittance.application.port.FindRemittancePort;
import com.pay.remittance.application.port.in.FindRemittanceCommand;
import com.pay.remittance.application.port.in.FindRemittanceUseCase;
import com.pay.remittance.domain.RemittanceRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class FindRemittanceService implements FindRemittanceUseCase {

  private final FindRemittancePort findRemittancePort;
  private final RemittanceRequestMapper mapper;

  @Override
  public List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command) {
    List<RemittanceRequestJpaEntity> remittanceHistory = findRemittancePort.findRemittanceHistory(
        command);
    return remittanceHistory.stream().map(mapper::mapToDomainEntity).toList();
  }

}
