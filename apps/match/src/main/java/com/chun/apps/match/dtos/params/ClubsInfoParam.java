package com.chun.apps.match.dtos.params;

import com.chun.crud.dtos.ClubsInfoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubsInfoParam {
    private String clubName1;
    private String clubCity1;
    private String clubDistrict1;

    private String clubName2;
    private String clubCity2;
    private String clubDistrict2;

    public ClubsInfoDto toClubsInfoDto() {
        return ClubsInfoDto.builder()
                .clubCity1(this.clubCity1)
                .clubCity2(this.clubCity2)
                .clubDistrict1(this.clubDistrict1)
                .clubDistrict2(this.clubDistrict2)
                .clubName1(this.clubName1)
                .clubName2(this.clubName2)
                .build();
    }
}
