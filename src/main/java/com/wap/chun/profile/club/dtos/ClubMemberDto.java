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
    private String city;
    private String district;
    private String pictureUri;
    private String registTime;
    private Integer uniformNum;

    private ClubMemberType type;
    private PositionType position;

    public ClubMemberDto(ClubMember entity) {
        this.memberId = entity.getMember().getId();
        this.memberName = entity.getMember().getName();
        this.city = entity.getMember().getCity();
        this.district = entity.getMember().getDistrict();
        this.pictureUri = entity.getMember().getPictureUri();
        this.registTime = entity.getRegistDate().toString();
        this.uniformNum = entity.getUniformNum();
        this.type = entity.getClubMemberType();
        this.position = entity.getPositionType();
    }
}
