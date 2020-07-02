package com.wap.chun.domain.entitys;

import com.wap.chun.domain.enums.PostCategory;

import java.time.LocalDateTime;

public class Post {
    private Long id;
    private PostCategory category;
    private String location;
    private Club clubId;
    private Member writer;
    private LocalDateTime createDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String message;
}
