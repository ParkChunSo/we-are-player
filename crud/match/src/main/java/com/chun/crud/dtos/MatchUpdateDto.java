package com.chun.crud.dtos;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MatchUpdateDto {
    private long id;
    private LocalDateTime date;
    private String city;
    private String district;
    private String detailsAddress;
}
