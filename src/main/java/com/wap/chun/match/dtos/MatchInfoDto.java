package com.wap.chun.match.dtos;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.Match;
import com.wap.chun.domain.enums.MatchState;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
@AllArgsConstructor
public class MatchInfoDto {
    private LocalDateTime date;
    private String location;
    private ClubInfoDto homeClub;
    private ClubInfoDto awayClub;
    private Integer homeClubScore;
    private Integer awayClubScore;
    private MatchState state;

    public MatchInfoDto(Match entity) {
        this.date = entity.getDate();
        this.location = entity.getLocation();
        this.homeClub = new ClubInfoDto(entity.getHomeClub());
        this.awayClub = new ClubInfoDto(entity.getAwayClub());
        this.homeClubScore = entity.getHomeClubScore();
        this.awayClubScore = entity.getAwayClubScore();
        this.state = entity.getState();
    }
}
