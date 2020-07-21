package com.wap.chun.domain.entitys;

import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.enums.PositionType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "club_member_tbl")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @CreationTimestamp
    private LocalDateTime registDate;

    @Setter
    private Integer uniformNum;

    @Setter
    @Enumerated(value = EnumType.STRING)
    private PositionType position;

    @Setter
    @Enumerated(value = EnumType.STRING)
    private ClubMemberType clubMemberType; //용병인지, 팀원인지 구분.

    @Builder
    public ClubMember(Club club, Member member) {
        this.club = club;
        this.member = member;
        this.position = member.getPosition();
        this.clubMemberType = ClubMemberType.MEMBER;
    }
}
