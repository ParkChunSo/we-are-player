package com.wap.chun.match.dtos;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.Match;
import com.wap.chun.domain.enums.MatchState;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchInfoDto {
    private long id;
    private LocalDateTime date;
    private String city;
    private String district;
    private String detailsAddress;
    private ClubInfoDto homeClub;
    private ClubInfoDto awayClub;
    private int homeClubScore;
    private int awayClubScore;
    private MatchState state;

    public MatchInfoDto(Match entity) {
        this.id = entity.getId();
        this.date = entity.getDate();
        this.city = entity.getCity();
        this.district = entity.getDistrict();
        this.detailsAddress = entity.getDetailsAddress();
        this.homeClub = new ClubInfoDto(entity.getHomeClub());
        this.awayClub = new ClubInfoDto(entity.getAwayClub());
        this.homeClubScore = entity.getHomeClubScore();
        this.awayClubScore = entity.getAwayClubScore();
        this.state = entity.getState();
    }

    public MatchInfoDto(MatchDetailsInfoDto dto){
        this.id = dto.getId();
        this.date = dto.getDate();
        this.city = dto.getCity();
        this.district = dto.getDistrict();
        this.detailsAddress = dto.getDetailsAddress();
        this.homeClub = dto.getHomeClub();
        this.awayClub = dto.getAwayClub();
        this.homeClubScore = dto.getHomeClubScore();
        this.awayClubScore = dto.getAwayClubScore();
        this.state = dto.getState();
    }
}
