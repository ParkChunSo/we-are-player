package com.wap.api.post.invitation.dto;

import com.wap.api.domain.entitys.Invitation;
import com.wap.api.domain.entitys.SubmitMatch;
import com.wap.api.post.submit.dtos.SubmitMatchInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class InvitationMatchInfoDto extends InvitationInfoDto {
    List<SubmitMatchInfoDto> dtos;

    public InvitationMatchInfoDto(Invitation entity) {
        super(entity);
    }

    public InvitationMatchInfoDto(Invitation entity, List<SubmitMatch> members) {
        super(entity);
        this.dtos = members.stream()
                .map(SubmitMatchInfoDto::new)
                .collect(Collectors.toList());
    }
}
