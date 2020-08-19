package com.wap.api.profile.club.dtos.response;

import com.wap.api.domain.entitys.Club;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubInfoDto {
    private long clubId;
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
        members = club.getClubMembers().stream()
                .map(ClubMemberDto::new)
                .collect(Collectors.toList());
    }
}
