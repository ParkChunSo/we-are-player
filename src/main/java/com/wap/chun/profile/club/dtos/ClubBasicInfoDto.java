package com.wap.chun.profile.club.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ClubBasicInfoDto {
    private String clubName;
    private String location;
    private String logoUri;
    private String LeaderId;
    private String leaderName;

    @Builder
    public ClubBasicInfoDto(String clubName, String location, String logoUri, String leaderId, String leaderName) {
        this.clubName = clubName;
        this.location = location;
        this.logoUri = logoUri;
        LeaderId = leaderId;
        this.leaderName = leaderName;
    }
}
