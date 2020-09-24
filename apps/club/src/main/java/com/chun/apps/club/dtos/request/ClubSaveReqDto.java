package com.chun.apps.club.dtos.request;

import com.chun.modules.crud.club.dtos.ClubSaveDto;
import lombok.Getter;

@Getter
public class ClubSaveReqDto {
    private String clubName;
    private String city;
    private String district;

    private ClubMemberSaveReqDto leader;

    public ClubSaveDto toClubSaveDto(String logoUri){
        return ClubSaveDto.builder()
                .clubCity(this.city)
                .clubDistrict(this.district)
                .clubName(this.clubName)
                .leader(this.leader.toClubMemberSaveDto())
                .logoUri(logoUri)
                .build();
    }
}
