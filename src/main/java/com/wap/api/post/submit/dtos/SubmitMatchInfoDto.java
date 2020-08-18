package com.wap.api.post.submit.dtos;

import com.wap.api.profile.domain.entitys.SubmitMatch;
import com.wap.api.profile.domain.enums.SubmitState;
import com.wap.api.profile.club.dtos.ClubInfoDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SubmitMatchInfoDto {
    private long id;
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
