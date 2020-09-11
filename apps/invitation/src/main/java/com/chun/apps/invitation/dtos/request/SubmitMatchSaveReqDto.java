package com.chun.apps.invitation.dtos.request;

import com.chun.crud.invitation.match.dtos.SubmitMatchSaveDto;
import lombok.Getter;

@Getter
public class SubmitMatchSaveReqDto {
    private long invitationId;
    private String message;
    
    // 요청하는 클럽 정보
    private String clubName;
    private String clubCity;
    private String clubDistrict;

    public SubmitMatchSaveDto toSubmitMatchSaveDto(){
        return SubmitMatchSaveDto.builder()
                .invitationId(this.invitationId)
                .message(this.message)
                .clubCity(this.clubCity)
                .clubDistrict(this.clubDistrict)
                .clubName(this.clubName)
                .build();
    }
}
