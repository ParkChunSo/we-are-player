package com.wap.chun.post.submit.dtos;

import lombok.Getter;

@Getter
public class SubmitMatchSaveDto {
    private long invitationId;
    private String message;
    
    // 요청하는 클럽 정보
    private String clubName;
    private String clubCity;
    private String clubDistrict;
}
