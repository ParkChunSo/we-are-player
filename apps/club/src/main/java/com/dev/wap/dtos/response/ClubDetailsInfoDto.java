package com.dev.wap.dtos.response;


import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ClubDetailsInfoDto extends ClubInfoDto{
    private List<MemberInfoDto> memberInfoDtos;

    public ClubDetailsInfoDto(Club club, List<ClubMember> entity) {
        super(club);
        this.memberInfoDtos = entity.stream()
                                .map(clubMember -> new MemberInfoDto(clubMember.getMember()))
                                .collect(Collectors.toList());
    }
}
