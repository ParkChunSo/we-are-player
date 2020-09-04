package com.chun.apps.club.dtos.response;

import com.chun.crud.entitys.Club;
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

    public ClubResDto(Club club) {
        this.clubId = club.getClubId();
        this.clubName = club.getClubName();
        this.city = club.getCity();
        this.district = club.getDistrict();
        this.logoUri = club.getLogoUri();
        members = club.getClubMembers().stream()
                .map(ClubMemberResDto::new)
                .collect(Collectors.toList());
    }
}
