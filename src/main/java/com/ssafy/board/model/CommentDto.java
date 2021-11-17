package com.ssafy.board.model;

import com.ssafy.board.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CommentDto {
    private String author;
    private String content;
    private Long postId;

    public Comment toEntity(){
        return Comment.builder()
                .author(author)
                .content(content)
                .commentTime(LocalDateTime.now())
                .build();
    }
}
