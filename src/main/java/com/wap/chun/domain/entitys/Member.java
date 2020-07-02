package com.wap.chun.domain.entitys;

import com.wap.chun.domain.enums.DisclosureScopeState;
import com.wap.chun.domain.enums.MemberRole;
import com.wap.chun.domain.enums.PositionType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Member {
    private String id;
    private String password;
    private String name;
    private String location;
    private LocalDateTime createDate;
    private Integer likeCnt;
    private Integer rudeCnt;
    private String pictureUri;
    private MemberRole role;
    private PositionType position;
    private DisclosureScopeState disclosureScopeState;
}
