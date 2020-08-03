package com.wap.chun.profile.club.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ClubInfoUpdateDto {
    // final 키워드 삽입 고려(Gson이 역직렬화할 수 있는지)
    private String clubName;
    private String location;

    private Integer likeCnt;
    private Integer rudeCnt;
    private String logoUri;
}
