package com.wap.chun.domain.entitys;

import com.sun.istack.NotNull;
import com.wap.chun.domain.enums.MatchState;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "match_tbl")
@Getter
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime date;
    @NotNull
    private String location;
    @ManyToOne
    @JoinColumn(name = "home_club_id")
    private Club homeClub;
    @ManyToOne
    @JoinColumn(name = "away_club_id")
    private Club awayClub;
    private Integer homeClubScore;
    private Integer awayClubScore;
    @Enumerated(value = EnumType.STRING)
    private MatchState state;

    public Match(LocalDateTime date, String location, Club homeClub, Club awayClub) {
        this.date = date;
        this.location = location;
        this.homeClub = homeClub;
        this.awayClub = awayClub;
    }

    public void setHomeClubScore(Integer homeClubScore) {
        this.homeClubScore = homeClubScore;
    }

    public void setAwayClubScore(Integer awayClubScore) {
        this.awayClubScore = awayClubScore;
    }

    public void setState(MatchState state) {
        this.state = state;
    }
}
