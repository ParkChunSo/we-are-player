package com.wap.chun.domain.entitys;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "board_tbl")
@Getter
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club clubId;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Member writer;

    @CreationTimestamp
    private LocalDateTime createDate;

    private String message;

    @Builder
    public Board(Club club, Member writer, String message) {
        this.clubId = club;
        this.writer = writer;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
