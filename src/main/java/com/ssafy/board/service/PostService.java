package com.ssafy.board.service;

import com.ssafy.board.model.PostCommentDto;
import com.ssafy.board.model.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    public String savePost(PostDto postDto);
    public Page<PostDto> getPostList(Pageable pageable);
    public PostCommentDto getPost(Long postId);
    public PostCommentDto updatePost(PostDto postDto);
    public void deletePost(Long postId);
}
