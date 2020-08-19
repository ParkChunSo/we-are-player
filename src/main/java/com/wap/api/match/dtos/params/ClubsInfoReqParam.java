package com.wap.api.match.dtos.params;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubsInfoReqParam {
    private String clubName1;
    private String clubCity1;
    private String clubDistrict1;

    private String clubName2;
    private String clubCity2;
    private String clubDistrict2;
}
