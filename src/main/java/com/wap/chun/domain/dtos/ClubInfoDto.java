package com.wap.chun.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClubInfoDto {
    private String clubName;
    private String location;
    private String logoUri;
    private String LeaderId;
    private String leaderName;
}
