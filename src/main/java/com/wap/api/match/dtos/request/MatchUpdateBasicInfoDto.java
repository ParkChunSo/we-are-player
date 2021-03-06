package com.wap.api.match.dtos.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MatchUpdateBasicInfoDto {
    private long id;
    private LocalDateTime date;
    private String city;
    private String district;
    private String detailsAddress;
}
