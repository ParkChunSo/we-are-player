package com.chun.apps.club.dtos.request;

import com.chun.modules.crud.club.dtos.ClubInfoDto;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ClubInfoUpdateReqDto {
    // final 키워드 삽입 고려(Gson이 역직렬화할 수 있는지)
    private String clubName;
    private String city;
    private String district;

    private int likeCnt;
    private int rudeCnt;

    public ClubInfoDto toClubInfoDto(){
        return ClubInfoDto.builder()
                .clubCity(this.city)
                .clubDistrict(this.district)
                .clubName(this.clubName)
                .build();
    }
}
