package com.wap.api.profile.member.dtos;

import com.wap.api.profile.domain.entitys.Club;
import com.wap.api.profile.domain.entitys.Member;
import com.wap.api.profile.club.dtos.ClubInfoDto;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemberDetailsInfoDto extends MemberInfoDto {
    private List<ClubInfoDto> clubInfoDtoList;

    public MemberDetailsInfoDto(Member entity) {
        super(entity);
    }

    public MemberDetailsInfoDto(Member member, List<Club> entityList) {
        super(member);
        this.clubInfoDtoList = entityList.isEmpty() ? Collections.emptyList()
                : entityList.stream().map(ClubInfoDto::new).collect(Collectors.toList());
    }
}
