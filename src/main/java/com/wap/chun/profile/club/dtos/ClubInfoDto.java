package com.wap.chun.profile.club.dtos;

import com.wap.chun.domain.entitys.Club;
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
    private String city;
    private String district;
    private String logoUri;
    @Setter
    private List<ClubMemberDto> members;

    public ClubInfoDto(Club club) {
        this.clubId = club.getClubId();
        this.clubName = club.getClubName();
        this.city = club.getCity();
        this.district = club.getDistrict();
        this.logoUri = club.getLogoUri();
        members = club.getClubMembers() == null ? new ArrayList<>()
                : club.getClubMembers().stream()
                .map(ClubMemberDto::new)
                .collect(Collectors.toList());
    }
}
