package com.wap.chun.post.submit.dtos;

import com.wap.chun.domain.entitys.SubmitMember;
import com.wap.chun.domain.enums.SubmitState;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import com.wap.chun.profile.member.dtos.MemberInfoDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SubmitMemberInfoDto {
    private long id;
    private String message;
    private LocalDateTime submitTime;
    private MemberInfoDto member;
    private ClubInfoDto club;
    private SubmitState state;

    public SubmitMemberInfoDto(SubmitMember entity){
        this.id = entity.getId();
        this.message = entity.getMessage();
        this.submitTime = entity.getSubmitTime();
        this.member = new MemberInfoDto(entity.getRequestMember());
        this.club = new ClubInfoDto(entity.getClub());
        this.state = entity.getState();
    }
}
