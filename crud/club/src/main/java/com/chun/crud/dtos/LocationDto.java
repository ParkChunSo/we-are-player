package com.chun.crud.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LocationDto {
    private String city;
    private String district;
}
