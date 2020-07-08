package com.wap.chun.domain.entitys;

import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.enums.PositionType;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "club_member_tbl")
@Getter
public class ClubMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @CreationTimestamp
    private LocalDateTime registDate;
    private Integer uniformNum;
    @Enumerated(value = EnumType.STRING)
    private PositionType position;
    @Enumerated(value = EnumType.STRING)
    private ClubMemberType clubMemberType; //용병인지, 팀원인지 구분.

    @Builder
    public ClubMember(Club club, Member member) {
        this.club = club;
        this.member = member;
        this.position = member.getPosition();
    }

    public void setUniformNum(Integer uniformNum) {
        this.uniformNum = uniformNum;
    }

    public void setPosition(PositionType position) {
        this.position = position;
    }
}
