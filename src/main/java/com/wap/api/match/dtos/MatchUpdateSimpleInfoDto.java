package com.wap.api.match.dtos;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MatchUpdateSimpleInfoDto {
    private long id;
    private LocalDateTime date;
    private String city;
    private String district;
    private String detailsAddress;
}
