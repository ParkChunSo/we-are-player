package com.wap.api.post.invitation.dto;

import com.wap.api.domain.entitys.Invitation;
import com.wap.api.domain.entitys.SubmitMember;
import com.wap.api.post.submit.dtos.SubmitMemberInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class InvitationMemberInfoDto extends InvitationInfoDto {
    List<SubmitMemberInfoDto> dtos;
    public InvitationMemberInfoDto(Invitation entity) {
        super(entity);
    }

    public InvitationMemberInfoDto(Invitation entity, List<SubmitMember> members) {
        super(entity);
        this.dtos = members.stream()
                .map(SubmitMemberInfoDto::new)
                .collect(Collectors.toList());
    }
}
