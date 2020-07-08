package com.wap.chun.domain.entitys;

import com.sun.istack.NotNull;
import com.wap.chun.domain.enums.PostCategory;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "post_tbl")
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private PostCategory category;
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
    private String message;

    @Builder
    public Post(PostCategory category, Club club, Member writer, LocalDateTime startDate, LocalDateTime endDate) {
        this.category = category;
        this.club = club;
        this.writer = writer;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setCategory(PostCategory category) {
        this.category = category;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
