package com.wap.chun.profile.member.dtos;

import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemberDetailsInfoDto extends MemberInfoDto {
    private List<ClubInfoDto> clubInfoDtoList;

    public MemberDetailsInfoDto(Member entity) {
        super(entity);
    }

    public MemberDetailsInfoDto(Member member, List<ClubMember> entityList) {
        super(member);
        this.clubInfoDtoList = entityList.stream()
                .map(ClubInfoDto::new)
                .collect(Collectors.toList());
    }
}
