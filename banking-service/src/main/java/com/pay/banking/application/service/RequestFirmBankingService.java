package com.pay.banking.application.service;

import com.pay.banking.adapter.out.persistence.FirmBankingRequestJpaEntity;
import com.pay.banking.adapter.out.persistence.FirmBankingRequestMapper;
import com.pay.banking.adapter.out.persistence.external.bank.ExternalFirmBankingRequest;
import com.pay.banking.adapter.out.persistence.external.bank.FirmBankingResult;
import com.pay.banking.application.port.in.RequestFirmBankingCommand;
import com.pay.banking.application.port.in.RequestFirmBankingUseCase;
import com.pay.banking.application.port.out.RequestExternalFirmBankingPort;
import com.pay.banking.application.port.out.RequestFirmBankingPort;
import com.pay.banking.domain.FirmBankingRequest;
import com.pay.common.UseCase;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmBankingService implements RequestFirmBankingUseCase {

  private final RequestFirmBankingPort requestFirmBankingPort;
  private final RequestExternalFirmBankingPort requestExternalFirmBankingPort;
  private final FirmBankingRequestMapper mapper;

  @Override
  public FirmBankingRequest requestFirmBanking(RequestFirmBankingCommand command) {
    // a->b 계좌
    // 1. 요청에 대한 정보를 먼저 write -> "요청" 상태로 우선 저장
    FirmBankingRequestJpaEntity firmBankingRequest = requestFirmBankingPort.createFirmBankingRequest(
        command.getFromBankName(),
        command.getFromBankAccountNumber(),
        command.getToBankName(),
        command.getToBankAccountNumber(),
        command.getAmount()
    );

    // 2. 외부 은행에 펌뱅킹 요청
    FirmBankingResult result = requestExternalFirmBankingPort.requestExternalFirmBanking(
        new ExternalFirmBankingRequest(
            command.getFromBankName(),
            command.getFromBankAccountNumber(),
            command.getToBankName(),
            command.getToBankAccountNumber(),
            command.getAmount())
    );

    // 3. 결과에 따라 1번에서 작성한 데이터 Update
    UUID resultUuid = UUID.randomUUID();
    firmBankingRequest.setUuid(resultUuid);

    if (result.isSuccess()) {
      // 성공 -> 1번에서 작성한 데이터를 "성공" 상태로 업데이트
      firmBankingRequest.setFirmBankingStatus(1);
    } else {
      // 실패 -> 1번에서 작성한 데이터를 "거절" 상태로 업데이트
      firmBankingRequest.setFirmBankingStatus(2);
    }

    // 4. 결과 리턴
    return mapper.mapToDomainEntity(
        requestFirmBankingPort.modifyFirmBankingRequest(firmBankingRequest),
        resultUuid
    );
  }
}
