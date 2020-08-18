package com.wap.chun.domain.builder;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.enums.PositionType;

public class ClubMemberBuilder {
    public static ClubMember buildMember(Member member, Club club){
        return ClubMember.builder()
                .club(club)
                .member(member)
                .clubMemberType(ClubMemberType.MEMBER)
                .positionType(PositionType.FW)
                .uniformNum(11)
                .build();
    }

    public static ClubMember buildLeader(Member member, Club club){
        return ClubMember.builder()
                .club(club)
                .member(member)
                .clubMemberType(ClubMemberType.LEADER)
                .positionType(PositionType.FW)
                .uniformNum(11)
                .build();
    }

}
