package com.chun.apps.invitation.dtos.request;

import com.wap.api.domain.enums.SubmitState;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SubmitMatchUpdateDto {
    private long id;
    private SubmitState state;
    private String city;
    private String district;
    private String detailsAddress;
    private LocalDateTime date;
}
