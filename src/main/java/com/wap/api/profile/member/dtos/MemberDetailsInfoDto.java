package com.wap.api.profile.member.dtos;

import com.wap.api.domain.entitys.Club;
import com.wap.api.domain.entitys.Member;
import com.wap.api.profile.club.dtos.response.ClubInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
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
