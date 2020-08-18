package com.wap.chun.post.submit.dtos;

import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.enums.PositionType;
import com.wap.chun.domain.enums.SubmitState;
import lombok.Getter;

@Getter
public class SubmitMemberUpdateDto {
    private Long id;
    private Integer uniformNum;
    private ClubMemberType clubMemberType;
    private PositionType positionType;
    private SubmitState state;
}