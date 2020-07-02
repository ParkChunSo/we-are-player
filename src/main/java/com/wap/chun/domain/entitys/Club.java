package com.wap.chun.domain.entitys;

import java.time.LocalDateTime;

public class Club {
    private Long id;
    private String name;
    private LocalDateTime createDate;
    private String location;
    private String logoUri;
    private Integer rudeCnt;
    private Integer point;

    private Member leader;
}
