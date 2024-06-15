package com.pay.banking.adapter.out.persistence;

import com.pay.banking.adapter.out.persistence.external.bank.ExternalFirmBankingRequest;
import com.pay.banking.adapter.out.persistence.external.bank.FirmBankingResult;
import com.pay.banking.application.port.out.RequestExternalFirmBankingPort;
import com.pay.banking.application.port.out.RequestFirmBankingPort;
import com.pay.common.PersistenceAdapter;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class FirmBankingRequestPersistenceAdapter implements RequestFirmBankingPort,
    RequestExternalFirmBankingPort {

  private final FirmBankingRequestRepository firmBankingRequestRepository;

  @Override
  public FirmBankingRequestJpaEntity createFirmBankingRequest(String fromBankName,
      String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int amount) {
    return firmBankingRequestRepository.save(new FirmBankingRequestJpaEntity(
        fromBankName,
        fromBankAccountNumber,
        toBankName,
        toBankAccountNumber,
        amount,
        0,
        UUID.randomUUID()
    ));
  }

  @Override
  public FirmBankingRequestJpaEntity modifyFirmBankingRequest(FirmBankingRequestJpaEntity entity) {
    return firmBankingRequestRepository.save(entity);
  }

  @Override
  public FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest request) {
    // 실제 외부 은행에 http 통신으로 펌뱅킹 요청 -> 결과를 FirmBankingResult 파싱
    // 임시로 성공으로 가정
    FirmBankingResult result = new FirmBankingResult(true);
    return result;
  }
}
