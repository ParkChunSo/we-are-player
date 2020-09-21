package com.chun.apps.club.dtos.params;

import com.chun.modules.crud.club.dtos.ClubDeleteDto;
import com.chun.modules.crud.club.dtos.ClubInfoDto;
import lombok.*;

@Getter @Setter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClubInfoParam {
    private String name;
    private String city;
    private String district;

    public ClubInfoDto toClubInfoDto(){
        return ClubInfoDto.builder()
                .clubCity(this.city)
                .clubDistrict(this.district)
                .clubName(this.name)
                .build();
    }

    public ClubDeleteDto toClubDeleteDto(){
        return ClubDeleteDto.builder()
                .clubCity(this.city)
                .clubDistrict(this.district)
                .clubName(this.name)
                .build();
    }
}
