package com.chun.crud.dtos;

import com.chun.crud.entitys.Club;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ClubCreateDto {
    private String clubName;
    private String clubCity;
    private String clubDistrict;
    private String logoUri;
    private ClubMemberCreateDto leader;
}
