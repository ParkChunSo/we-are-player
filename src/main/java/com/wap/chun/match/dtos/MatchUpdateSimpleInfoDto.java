package com.wap.chun.match.dtos;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MatchUpdateSimpleInfoDto {
    private Long id;
    private LocalDateTime date;
    private String city;
    private String district;
    private String detailsAddress;
}
