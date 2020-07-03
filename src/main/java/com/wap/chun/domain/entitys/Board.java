package com.wap.chun.domain.entitys;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "board_tbl")
@Getter
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Member writer;
    @CreationTimestamp
    private LocalDateTime createDate;
    private String message;

    public Board(Club club, Member writer, String message) {
        this.club = club;
        this.writer = writer;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
