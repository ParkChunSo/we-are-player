package com.chun.crud.entitys;

import com.chun.commons.enums.ClubMemberType;
import com.chun.commons.enums.PositionType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "club_member_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    private int uniformNum;

    @Enumerated(value = EnumType.STRING)
    private PositionType positionType;

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

    public ClubMember updateLeader(Member newLeader){
        this.member = newLeader;

        return this;
    }

    public ClubMember updateInfo(int uniformNum, PositionType type){
        this.uniformNum = uniformNum;
        this.positionType = type;

        return this;
    }

    public ClubMember updateClubMemberType(ClubMemberType type){
        this.clubMemberType = type;
        return this;
    }
}