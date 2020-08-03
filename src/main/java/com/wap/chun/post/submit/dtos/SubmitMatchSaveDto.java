package com.wap.chun.post.submit.dtos;

import lombok.Getter;

@Getter
public class SubmitMatchSaveDto {
    private Long invitationId;
    private String message;
    private String clubName;
    private String clubCity;
    private String clubDistrict;
}
