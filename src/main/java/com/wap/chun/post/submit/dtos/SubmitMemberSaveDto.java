package com.wap.chun.post.submit.dtos;

import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.enums.SubmitState;
import com.wap.chun.domain.enums.SubmitType;
import lombok.Getter;

@Getter
public class SubmitMemberSaveDto {
    private Long invitationId;
    private String message;
    private String memberId;
    private ClubMemberType clubMemberType;
}
