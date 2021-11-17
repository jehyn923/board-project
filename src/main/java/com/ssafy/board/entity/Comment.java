package com.ssafy.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "COMMENTS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(name = "COMMENT_CONTENT")
    private String content;

    @Column(name = "COMMENT_AUTHOR")
    private String author;

    @Column(name = "COMMENT_DATE")
    private LocalDateTime commentTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="POST_ID")
    private Post post;
}
