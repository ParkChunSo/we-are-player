package com.wap.api.profile.club.dtos.response;

import com.wap.api.domain.entitys.ClubMember;
import com.wap.api.domain.enums.ClubMemberType;
import com.wap.api.domain.enums.PositionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor //테스트할 때 Objectmapper가 맵핑하는 과정 중 필요로하기 때문 
@AllArgsConstructor
public class ClubMemberDto {
    private String memberId;
    private String memberName;
    private String city;
    private String district;
    private String pictureUri;
    private String registTime;
    private int uniformNum;

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
