package com.chun.modules.crud.invitation.match.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class SubmitMatchSaveDto {
    private long invitationId;
    private String message;
    
    // 요청하는 클럽 정보
    private String clubName;
    private String clubCity;
    private String clubDistrict;
}
