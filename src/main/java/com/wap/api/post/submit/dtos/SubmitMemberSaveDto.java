package com.wap.api.post.submit.dtos;

import com.wap.api.domain.enums.ClubMemberType;
import lombok.Getter;

@Getter
public class SubmitMemberSaveDto {
    private long invitationId;
    private String message;
    private String memberId;
    private ClubMemberType clubMemberType;
}
