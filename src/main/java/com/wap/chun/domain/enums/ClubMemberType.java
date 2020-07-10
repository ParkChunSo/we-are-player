package com.wap.chun.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClubMemberType {
    MERCENARY("용병"), MEMBER("팀원");

    private String name;
}
