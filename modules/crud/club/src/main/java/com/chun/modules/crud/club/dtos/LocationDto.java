package com.chun.modules.crud.club.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LocationDto {
    private String city;
    private String district;
}
