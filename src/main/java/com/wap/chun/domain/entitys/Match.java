package com.wap.chun.domain.entitys;

import com.sun.istack.NotNull;
import com.wap.chun.domain.enums.MatchState;
import com.wap.chun.match.dtos.MatchSaveDto;
import com.wap.chun.match.dtos.MatchUpdateSimpleInfoDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "match_tbl")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private String city;

    @NotNull
    private String district;

    @NotNull
    private String detailsAddress;

    @ManyToOne
    @JoinColumn(name = "home_club_id")
    private Club homeClub;

    @ManyToOne
    @JoinColumn(name = "away_club_id")
    private Club awayClub;

    private Integer homeClubScore;

    private Integer awayClubScore;

    @Setter
    @Enumerated(value = EnumType.STRING)
    private MatchState state;

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Goal> goals = new ArrayList<>();

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Squad> squads = new ArrayList<>();

    @Builder
    public Match(LocalDateTime date, String city, String district, String detailsAddress, Club homeClub, Club awayClub) {
        this.date = date;
        this.city = city;
        this.district = district;
        this.detailsAddress = detailsAddress;
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.state = date.isAfter(LocalDateTime.now()) ? MatchState.FUTURE : MatchState.PAST;
    }

    public Match updateInfoAfterMatch(Integer homeClubScore, Integer awayClubScore, List<Goal> goals, List<Squad> squads){
        this.homeClubScore = homeClubScore;
        this.awayClubScore = awayClubScore;
        this.goals = goals;
        this.squads = squads;
        this.state = MatchState.PAST;

        return this;
    }

    public Match updateInfo(MatchUpdateSimpleInfoDto dto){
        this.date = dto.getDate();
        this.city = dto.getCity();
        this.district = dto.getDistrict();
        this.detailsAddress = dto.getDetailsAddress();
        return this;
    }
}
