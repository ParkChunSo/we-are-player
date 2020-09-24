package com.wap.api.match.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
@AllArgsConstructor
public class MatchSaveDto {
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
}
