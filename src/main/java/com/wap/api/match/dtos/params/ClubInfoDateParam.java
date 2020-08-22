package com.wap.api.match.dtos.params;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClubInfoDateParam {
    private String clubName;
    private String clubCity;
    private String clubDistrict;

    private LocalDate from;
    private LocalDate to;
}
