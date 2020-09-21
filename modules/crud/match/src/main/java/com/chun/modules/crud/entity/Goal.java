package com.chun.modules.crud.entity;

import com.chun.commons.enums.MatchType;
import com.chun.commons.errors.exception.InvalidInputValueException;
import com.chun.modules.crud.member.entitys.Member;
import lombok.*;

import javax.persistence.*;

@Entity(name = "goal_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Goal {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int time; //분만 저장(ex 34: 전반 34분)

    private MatchType type;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member goalMember;

    //어시스트는 없을 수도 있기 때문
    private String assistMemberId;
    private String assistMemberName;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @Builder
    public Goal(int time, MatchType type, Member goalMember, String assistMemberId, String assistMemberName, Match match){
        if(time < 0 || time >100)
            throw new InvalidInputValueException();
        this.time = time;
        this.type = type;
        this.goalMember = goalMember;
        this.assistMemberId = assistMemberId;
        this.assistMemberName = assistMemberName;
        this.match = match;
    }
}
