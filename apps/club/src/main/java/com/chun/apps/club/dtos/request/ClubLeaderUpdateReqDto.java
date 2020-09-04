package com.chun.apps.club.dtos.request;

import com.chun.crud.dtos.ClubLeaderUpdateDto;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ClubLeaderUpdateReqDto {
    // final 키워드 삽입 고려(Gson이 역직렬화할 수 있는지)
    private String clubName;
    private String city;
    private String district;

    private String preLeaderId;
    private String newLeaderId;

    public ClubLeaderUpdateDto toClubLeaderUpdateDto(){
        return ClubLeaderUpdateDto.builder()
                .clubName(this.clubName)
                .city(this.city)
                .district(this.district)
                .preLeaderId(this.preLeaderId)
                .newLeaderId(this.newLeaderId)
                .build();
    }
}

