package com.pay.remittance.adapter.out.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRemittanceRequestRepository extends JpaRepository<RemittanceRequestJpaEntity, Long> {

  List<RemittanceRequestJpaEntity> findByFromMembershipId(Long fromMembershipId);
}
