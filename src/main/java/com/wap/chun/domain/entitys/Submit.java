package com.wap.chun.domain.entitys;

import com.wap.chun.domain.enums.SubmitState;
import com.wap.chun.domain.enums.SubmitType;

public class Submit {
    private Long id;
    private String message;

    private Club club;
    private Member member;

    private SubmitType type;
    private SubmitState state;
}
