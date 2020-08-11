package com.wap.chun.profile.club.dtos;

import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.enums.PositionType;
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

