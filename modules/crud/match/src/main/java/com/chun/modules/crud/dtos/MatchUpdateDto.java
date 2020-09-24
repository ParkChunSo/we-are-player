package com.chun.modules.crud.dtos;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MatchUpdateDto {
    private long id;
    private LocalDateTime date;
    private String city;
    private String district;
    private String detailsAddress;
}
