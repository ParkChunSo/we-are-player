package com.chun.crud.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubInfoDto {
    private String name;
    private String city;
    private String district;
}
