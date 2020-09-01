package com.chun.crud.dtos;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
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
