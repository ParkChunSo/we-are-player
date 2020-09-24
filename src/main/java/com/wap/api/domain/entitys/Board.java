package com.wap.api.domain.entitys;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "board_tbl")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club clubId;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Member writer;

    @CreationTimestamp
    private LocalDateTime createDate;

    @Setter
    private String message;

    @Builder
    public Board(Club club, Member writer, String message) {
        this.clubId = club;
        this.writer = writer;
        this.message = message;
    }
}
