package com.ssafy.board.service;

import com.ssafy.board.model.CommentDto;

public interface CommentService {
    public String addComment(CommentDto commentDto);
    public void deleteComment(Long commentId);

}
