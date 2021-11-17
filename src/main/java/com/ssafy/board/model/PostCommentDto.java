package com.ssafy.board.model;

import com.ssafy.board.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class PostCommentDto {
    private PostDto postDto;
    private List<CommentResponseDto> commentList;
}
