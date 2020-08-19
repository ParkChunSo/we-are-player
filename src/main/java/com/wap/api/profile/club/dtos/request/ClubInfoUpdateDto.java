package com.wap.api.profile.club.dtos.request;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ClubInfoUpdateDto {
    // final 키워드 삽입 고려(Gson이 역직렬화할 수 있는지)
    private String clubName;
    private String city;
    private String district;

    private int likeCnt;
    private int rudeCnt;
    private String logoUri;
}
