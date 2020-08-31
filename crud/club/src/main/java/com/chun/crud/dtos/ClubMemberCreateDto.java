package com.chun.crud.dtos;

import com.chun.commons.enums.ClubMemberType;
import com.chun.commons.enums.PositionType;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ClubMemberCreateDto {
    private String memberId;
    private String clubName;
    private String clubCity;
    private String clubDistrict;
    private int uniformNum;
    private PositionType positionType;
    private ClubMemberType clubMemberType;
}
