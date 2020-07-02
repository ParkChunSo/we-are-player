package com.wap.chun.domain.entitys;

import java.time.LocalDateTime;

public class Board {
    private Long id;
    private Club club;
    private Member writer;
    private LocalDateTime createDate;
    private String message;
}
