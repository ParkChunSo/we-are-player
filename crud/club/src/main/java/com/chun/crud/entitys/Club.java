package com.chun.crud.entitys;

import com.sun.istack.NotNull;
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
@EqualsAndHashCode(of = {"clubId", "clubName", "city", "district"})
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long clubId;

    @NotNull
    private String clubName;

    @CreationTimestamp
    private LocalDateTime createDate;

    private String city;

    private String district;

    private String logoUri;

    private int likeCnt;

    private int rudeCnt;

    private int point;

    @Setter
    @OneToMany(mappedBy = "club", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ClubMember> clubMembers = new ArrayList<>();

    @Builder
    public Club(String clubName, String city, String district, String logoUri) {
        this.clubName = clubName;
        this.city = city;
        this.district = district;
        this.logoUri = logoUri;
        this.likeCnt = 0;
        this.rudeCnt = 0;
        this.point = 0;
    }

    public Club updateInfo(String logoUri, int likeCnt, int rudeCnt, int point){
        this.logoUri = logoUri;
        this.likeCnt = likeCnt;
        this.rudeCnt = rudeCnt;
        this.point = point;

        return this;
    }
}
