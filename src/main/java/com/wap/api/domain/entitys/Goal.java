package com.wap.api.domain.entitys;

import com.wap.api.domain.enums.MatchType;
import com.wap.api.error.exception.InvalidInputValueException;
import com.wap.api.match.dtos.reponse.GoalInfoDto;
import lombok.*;

import javax.persistence.*;

@Entity(name = "goal_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Goal {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int time;

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
    public Goal(GoalInfoDto dto, MatchType type, Member goalMember, Match match){
        if(dto.getTime() < 0 || dto.getTime() >100)
            throw new InvalidInputValueException();
        this.time = dto.getTime();
        this.type = type;
        this.goalMember = goalMember;
        this.assistMemberId = dto.getAssistMemberId();
        this.assistMemberName = dto.getAssistMemberName();
        this.match = match;
    }
}
