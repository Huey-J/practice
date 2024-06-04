package com.pay.membership.adapter.in.web;

import com.pay.common.WebAdapter;
import com.pay.membership.application.port.in.ModifyMembershipCommand;
import com.pay.membership.application.port.in.ModifyMembershipUseCase;
import com.pay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ModifyMembershipController {

  private final ModifyMembershipUseCase modifyMembershipUseCase;

  @PutMapping("/membership/{membershipId}")
  ResponseEntity<Membership> modifyMembership(@PathVariable Long membershipId,
      @RequestBody ModifyMembershipRequest request) {
    ModifyMembershipCommand command = ModifyMembershipCommand.builder()
        .membershipId(membershipId)
        .name(request.getName())
        .address(request.getAddress())
        .email(request.getEmail())
        .isValid(request.isValid())
        .isCorp(request.isCorp())
        .build();

    return ResponseEntity.ok(modifyMembershipUseCase.modifyMembership((command)));
  }

}
