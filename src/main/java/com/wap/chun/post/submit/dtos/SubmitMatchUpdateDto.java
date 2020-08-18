package com.wap.chun.post.submit.dtos;

import com.wap.chun.domain.enums.SubmitState;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SubmitMatchUpdateDto {
    private Long id;
    private SubmitState state;
    private String city;
    private String district;
    private String detailsAddress;
    private LocalDateTime date;
}
