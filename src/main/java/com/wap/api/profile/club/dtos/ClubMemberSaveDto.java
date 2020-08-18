package com.wap.api.profile.club.dtos;

import com.wap.api.profile.domain.enums.ClubMemberType;
import com.wap.api.profile.domain.enums.PositionType;
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

