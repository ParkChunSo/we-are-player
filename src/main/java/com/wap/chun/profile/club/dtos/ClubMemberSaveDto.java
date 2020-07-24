package com.wap.chun.profile.club.dtos;

import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.enums.PositionType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class ClubMemberSaveDto {
    private String clubName;
    private String clubLocation;
    private String memberId;
    private Integer uniformNum;
    private PositionType positionType;
    private ClubMemberType clubMemberType;


}

