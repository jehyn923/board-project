package com.ssafy.board.entity;

import com.ssafy.board.model.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "POSTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_title")
    private String title;

    @Column(name = "post_author")
    private String author;

    @Column(name = "post_date")
    private LocalDateTime postDate;

    @Column(name = "post_content")
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    public Post addComment(Comment comment){
        comments.add(comment);
        return this;
    }
    public Post updatePost(Post updatedPost){
        if(StringUtils.hasText(updatedPost.getAuthor())){
            this.author = updatedPost.getAuthor();
        }
        if(StringUtils.hasText(updatedPost.getContent())){
            this.content = updatedPost.getContent();
        }
        if(StringUtils.hasText(updatedPost.getTitle())){
            this.title = updatedPost.getTitle();
        }
        return this;
    }
}