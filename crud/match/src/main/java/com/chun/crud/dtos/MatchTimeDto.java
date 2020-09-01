package com.chun.crud.dtos;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MatchTimeDto {
    private LocalDate from;
    private LocalDate to;
}
