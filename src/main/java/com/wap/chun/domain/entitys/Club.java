package com.wap.chun.domain.entitys;

import com.sun.istack.NotNull;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "club_tbl")
@Getter
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubId;
    @NotNull
    private String clubName;
    @CreationTimestamp
    private LocalDateTime createDate;
    private String location;
    private String logoUri;
    private Integer likeCnt;
    private Integer rudeCnt;
    private Integer point;

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

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLogoUri(String logoUri) {
        this.logoUri = logoUri;
    }

    public void setLikeCnt(Integer likeCnt) {
        this.likeCnt = likeCnt;
    }

    public void setRudeCnt(Integer rudeCnt) {
        this.rudeCnt = rudeCnt;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public void setLeader(Member leader) {
        this.leader = leader;
    }
}
