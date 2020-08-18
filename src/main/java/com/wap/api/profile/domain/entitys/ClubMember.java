package com.wap.api.profile.domain.entitys;

import com.wap.api.profile.domain.enums.ClubMemberType;
import com.wap.api.profile.domain.enums.PositionType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "club_member_tbl")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ClubMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @CreationTimestamp
    private LocalDateTime registDate;

    @Setter
    private int uniformNum;

    @Setter
    @Enumerated(value = EnumType.STRING)
    private PositionType positionType;

    @Setter
    @Enumerated(value = EnumType.STRING)
    private ClubMemberType clubMemberType;

    @Builder
    public ClubMember(Club club, Member member, int uniformNum, PositionType positionType, ClubMemberType clubMemberType) {
        this.club = club;
        this.member = member;
        this.uniformNum = uniformNum;
        this.positionType = positionType;
        this.clubMemberType = clubMemberType;
    }
}
