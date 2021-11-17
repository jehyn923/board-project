package com.ssafy.board.model;

import com.ssafy.board.entity.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime postDate;

    public Post toEntity(){
        return Post.builder()
                .author(author)
                .content(content)
                .title(title)
                .postDate(LocalDateTime.now())
                .build();
    }
}
