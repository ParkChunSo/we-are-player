package com.chun.modules.crud.club.dtos;

import com.chun.commons.enums.PositionType;
import lombok.Getter;

@Getter
public class ClubMemberUpdateDto {
    private String clubName;
    private String city;
    private String district;
    private String memberId;
    private int uniformNum;
    private PositionType positionType;

}
