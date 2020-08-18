package com.wap.api.profile.domain.entitys;

import com.wap.api.profile.domain.enums.MatchType;
import com.wap.api.profile.domain.enums.PositionType;
import lombok.*;

import javax.persistence.*;

@Entity(name = "squad_tbl")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Squad {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private PositionType positionType;

    private MatchType matchType;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;
}
