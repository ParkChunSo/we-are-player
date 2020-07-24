package com.wap.chun.profile.club.dtos;

import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.enums.PositionType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubMemberDto {
    private String memberId;
    private String memberName;
    private String location;
    private String pictureUri;
    private String registTime;

    private ClubMemberType type;
    private PositionType position;

    public ClubMemberDto(ClubMember entity) {
        this.memberId = entity.getMember().getId();
        this.memberName = entity.getMember().getName();
        this.location = entity.getMember().getLocation();
        this.pictureUri = entity.getMember().getPictureUri();
        this.registTime = entity.getRegistDate().toString();
        this.type = entity.getClubMemberType();
        this.position = entity.getPosition();
    }
}
