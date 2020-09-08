package com.chun.apps.match.dtos.request;

import com.chun.crud.dtos.ClubInfoDto;
import com.chun.crud.dtos.MatchUpdateDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MatchUpdateReqDto {
    private long id;
    private LocalDateTime date;
    private String city;
    private String district;
    private String detailsAddress;

    public MatchUpdateDto toMatchUpdateDto(){
        return MatchUpdateDto.builder()
                .id(this.id)
                .date(this.date)
                .city(this.city)
                .district(this.district)
                .detailsAddress(this.detailsAddress)
                .build();
    }
}
