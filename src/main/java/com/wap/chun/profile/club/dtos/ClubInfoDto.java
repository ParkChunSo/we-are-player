package com.wap.chun.profile.club.dtos;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.profile.member.dtos.MemberInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Builder
@AllArgsConstructor
public class ClubInfoDto {
    private Long clubId;
    private String clubName;
    private String location;
    private String logoUri;
    @Setter
    private List<ClubMemberDto> members;

    public ClubInfoDto(Club club) {
        this.clubId = club.getClubId();
        this.clubName = club.getClubName();
        this.location = club.getLocation();
        this.logoUri = club.getLogoUri();
        members = club.getClubMembers() == null ? new ArrayList<>()
                : club.getClubMembers().stream()
                .map(ClubMemberDto::new)
                .collect(Collectors.toList());
    }
}
