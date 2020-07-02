package com.wap.chun.domain.entitys;

import com.wap.chun.domain.enums.MatchState;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Match {
    private Long id;
    private LocalDateTime date;
    private String location;
    private Club homeClub;
    private Club awayClub;
    private Integer homeClubScore;
    private Integer awayClubScore;
    private MatchState state;
}
