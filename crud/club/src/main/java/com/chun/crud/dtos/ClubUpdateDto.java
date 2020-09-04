package com.chun.crud.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubUpdateDto {
    private String clubName;
    private String city;
    private String district;

    private int likeCnt;
    private int rudeCnt;
    private int point;
    private String logoUri;
}
