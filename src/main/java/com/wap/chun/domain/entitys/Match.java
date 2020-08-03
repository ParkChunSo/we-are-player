package com.wap.chun.domain.entitys;

import com.sun.istack.NotNull;
import com.wap.chun.domain.enums.MatchState;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "match_tbl")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Setter
    private Integer homeClubScore;

    @Setter
    private Integer awayClubScore;

    @Setter
    @Enumerated(value = EnumType.STRING)
    private MatchState state;

    @Builder
    public Match(LocalDateTime date, String location, Club homeClub, Club awayClub) {
        this.date = date;
        this.location = location;
        this.homeClub = homeClub;
        this.awayClub = awayClub;
    }
}
