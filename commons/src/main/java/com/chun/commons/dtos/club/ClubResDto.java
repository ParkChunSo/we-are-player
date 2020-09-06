package com.chun.commons.dtos.club;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubResDto {
    private long clubId;
    private String clubName;
    private String city;
    private String district;
    @Setter
    private String logoUri;
    @Setter
    private List<ClubMemberResDto> members;
}
