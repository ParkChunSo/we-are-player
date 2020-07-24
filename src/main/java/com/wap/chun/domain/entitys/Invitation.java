package com.wap.chun.domain.entitys;

import com.sun.istack.NotNull;
import com.wap.chun.domain.enums.PostCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "post_tbl")
@Getter
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private PostCategory category;

    @Setter
    private String location;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Member writer;

    @CreationTimestamp
    private LocalDateTime createDate;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @Setter
    private String message;

    @Builder
    public Invitation(PostCategory category, Club club, Member writer, LocalDateTime startDate, LocalDateTime endDate) {
        this.category = category;
        this.club = club;
        this.writer = writer;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
