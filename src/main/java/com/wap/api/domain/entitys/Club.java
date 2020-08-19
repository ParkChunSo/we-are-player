package com.wap.api.domain.entitys;

import com.sun.istack.NotNull;
import com.wap.api.profile.club.dtos.response.ClubInfoDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "club_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(of = "clubId")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long clubId;

    @NotNull
    private String clubName;

    @CreationTimestamp
    private LocalDateTime createDate;

    @Setter
    private String city;

    @Setter
    private String district;

    @Setter
    private String logoUri;

    @Setter
    private int likeCnt;

    @Setter
    private int rudeCnt;

    @Setter
    private int point;

    private boolean deleteFlag;

    @Setter
    @OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
    private List<ClubMember> clubMembers = new ArrayList<>();

    @Builder
    protected Club(String clubName, String city, String district, String logoUri) {
        this.clubName = clubName;
        this.city = city;
        this.district = district;
        this.logoUri = logoUri;
        this.likeCnt = 0;
        this.rudeCnt = 0;
        this.point = 0;
        this.deleteFlag = false;
    }

    public Club(ClubInfoDto dto) {
        this.clubName = dto.getClubName();
        this.city = dto.getCity();
        this.district = dto.getDistrict();
        this.logoUri = dto.getLogoUri();
        this.likeCnt = 0;
        this.rudeCnt = 0;
        this.point = 0;
        this.deleteFlag = false;
    }

    public void deleteClub(){
        deleteFlag = true;
    }
}
