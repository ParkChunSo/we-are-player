package com.chun.modules.crud.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class MatchLocationDto {
    private String city;
    private String district;
}
