package com.wap.chun.profile.club.dtos;

import com.wap.chun.domain.enums.PositionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class ClubMemberDto {
    private String memberName;
    private String memberId;
    private String location;
    private String pictureUri;
    private PositionType position;
}
