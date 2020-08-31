package com.dev.wap.dtos.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubMemberSaveDto {
    private String clubName;
    private String clubCity;
    private String clubDistrict;
    private String memberId;
    private int uniformNum;
    private PositionType positionType;
    private ClubMemberType clubMemberType;
}

