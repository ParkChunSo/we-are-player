package com.chun.apps.match.dtos.request;

import com.chun.crud.dtos.MatchSaveDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
@AllArgsConstructor
public class MatchSaveReqDto {
    private LocalDateTime date;
    private String matchCity;
    private String matchDistrict;
    private String matchDetailsAddress;

    private String homeClubName;
    private String homeClubCity;
    private String homeClubDistrict;

    private String awayClubName;
    private String awayClubCity;
    private String awayClubDistrict;

    public MatchSaveDto toMatchSaveDto(){
        return MatchSaveDto.builder()
                .date(this.date)
                .matchCity(this.matchCity)
                .matchDistrict(this.matchDistrict)
                .matchDetailsAddress(this.matchDetailsAddress)
                .homeClubCity(this.homeClubCity)
                .homeClubDistrict(this.homeClubDistrict)
                .homeClubName(this.homeClubName)
                .awayClubCity(this.awayClubCity)
                .awayClubDistrict(this.awayClubDistrict)
                .awayClubName(this.awayClubName)
                .build();
    }
}
