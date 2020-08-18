package com.wap.api.profile.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClubMemberType {
    MERCENARY("용병"), MEMBER("팀원"), LEADER("리더");

    private String name;
}
