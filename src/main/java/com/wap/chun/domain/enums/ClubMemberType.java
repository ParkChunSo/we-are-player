package com.wap.chun.domain.enums;

public enum ClubMemberType {
    MERCENARY("용병"), MEMBER("팀원");
    private String name;

    ClubMemberType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
