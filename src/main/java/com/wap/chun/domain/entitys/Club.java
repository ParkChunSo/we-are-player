package com.wap.chun.domain.entitys;

import com.sun.istack.NotNull;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "club_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "clubId")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clubId;

    @NotNull
    private String clubName;

    @CreationTimestamp
    private LocalDateTime createDate;

    @Setter
    private String location;

    @Setter
    private String logoUri;

    @Setter
    private Integer likeCnt;

    @Setter
    private Integer rudeCnt;

    @Setter
    private Integer point;

    @Setter
    @OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
    private List<ClubMember> clubMembers;

    private boolean deleteFlag = false;

    @Builder
    protected Club(String clubName, String location, String logoUri) {
        this.clubName = clubName;
        this.location = location;
        this.logoUri = logoUri;
        this.likeCnt = 0;
        this.rudeCnt = 0;
        this.point = 0;
    }

    public Club(ClubInfoDto dto) {
        this.clubName = dto.getClubName();
        this.location = dto.getLocation();
        this.logoUri = dto.getLogoUri();
        this.likeCnt = 0;
        this.rudeCnt = 0;
        this.point = 0;
    }

    public void deleteClub(){
        deleteFlag = true;
    }
}
