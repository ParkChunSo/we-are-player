package com.chun.crud.dtos;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter @Builder
public class MatchTimeDto {
    private LocalDate from;
    private LocalDate to;
}
