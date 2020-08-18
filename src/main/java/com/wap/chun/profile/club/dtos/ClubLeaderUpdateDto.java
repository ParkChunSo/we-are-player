package com.wap.chun.profile.club.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ClubLeaderUpdateDto {
    // final 키워드 삽입 고려(Gson이 역직렬화할 수 있는지)
    private String clubName;
    private String city;
    private String district;

    private String preLeaderId;
    private String newLeaderId;
}

