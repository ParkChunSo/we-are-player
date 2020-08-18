package com.wap.api.match.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
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
