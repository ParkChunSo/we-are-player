package com.wap.chun.post.submit.dtos;

import com.wap.chun.domain.entitys.SubmitMatch;
import com.wap.chun.domain.enums.SubmitState;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SubmitMatchInfoDto {
    private Long id;
    private String message;
    private LocalDateTime submitTime;
    private ClubInfoDto club;
    private ClubInfoDto requestClub;
    private SubmitState state;

    public SubmitMatchInfoDto(SubmitMatch entity){
        this.id = entity.getId();
        this.message = entity.getMessage();
        this.submitTime = entity.getSubmitTime();
        this.club = new ClubInfoDto(entity.getClub());
        this.requestClub = new ClubInfoDto(entity.getRequestClub());
        this.state = entity.getState();
    }
}
