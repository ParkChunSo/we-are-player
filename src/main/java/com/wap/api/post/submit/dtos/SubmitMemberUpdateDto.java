package com.wap.api.post.submit.dtos;

import com.wap.api.profile.domain.enums.ClubMemberType;
import com.wap.api.profile.domain.enums.PositionType;
import com.wap.api.profile.domain.enums.SubmitState;
import lombok.Getter;

@Getter
public class SubmitMemberUpdateDto {
    private long id;
    private int uniformNum;
    private ClubMemberType clubMemberType;
    private PositionType positionType;
    private SubmitState state;
}
