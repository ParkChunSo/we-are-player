package com.chun.apps.match.dtos.params;

import com.chun.modules.crud.club.dtos.ClubInfoDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClubInfoParam {
    private String clubName;
    private String clubCity;
    private String clubDistrict;

    public ClubInfoDto toClubInfoDto(){
        return ClubInfoDto.builder()
                .clubCity(this.clubCity)
                .clubDistrict(this.clubDistrict)
                .clubName(this.clubName)
                .build();
    }
}
