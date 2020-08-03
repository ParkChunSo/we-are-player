package com.wap.chun.domain.entitys;

import com.wap.chun.domain.enums.MatchType;
import com.wap.chun.match.dtos.GoalInfoDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Entity(name = "goal_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Goal {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer time;

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
        this.time = dto.getTime();
        this.type = type;
        this.goalMember = goalMember;
        this.assistMemberId = dto.getAssistMemberId();
        this.assistMemberName = dto.getAssistMemberName();
        this.match = match;
    }
}
