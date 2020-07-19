package com.wap.chun.domain.entitys;

import com.sun.istack.NotNull;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "club_tbl")
@Getter
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
    @OneToOne
    @JoinColumn(name = "leader_id")
    private Member leader;

    @Builder
    public Club(String clubName,String location, Member leader) {
        this.clubName = clubName;
        this.location = location;
        this.leader = leader;
        this.likeCnt = 0;
        this.rudeCnt = 0;
        this.point = 0;
    }

    public Club(ClubInfoDto dto, Member leader){
        this.clubName = dto.getClubName();
        this.location = dto.getLocation();
        this.logoUri = dto.getLogoUri();
        this.leader = leader;
        this.likeCnt = 0;
        this.rudeCnt = 0;
        this.point = 0;
    }
}
