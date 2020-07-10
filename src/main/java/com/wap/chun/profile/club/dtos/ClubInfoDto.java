package com.wap.chun.profile.club.dtos;

import com.wap.chun.domain.entitys.Club;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter @Builder
public class ClubInfoDto {
    private Long clubId;
    private String clubName;
    private String location;
    private String logoUri;
    private String leaderId;
    private String leaderName;

    public ClubInfoDto(Club club) {
        this.clubId = club.getClubId();
        this.clubName = club.getClubName();
        this.location = club.getLocation();
        this.logoUri = club.getLogoUri();
        this.leaderId = club.getLeader().getId();
        this.leaderName = club.getLeader().getName();
    }
}
