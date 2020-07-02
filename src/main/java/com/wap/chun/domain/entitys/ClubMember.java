package com.wap.chun.domain.entitys;

import com.wap.chun.domain.enums.PositionType;

import java.time.LocalDateTime;

public class ClubMember {
    private Long id;
    private Club club;
    private Member member;
    private LocalDateTime registDate;
    private Integer uniformNum;
    private PositionType position;
}
