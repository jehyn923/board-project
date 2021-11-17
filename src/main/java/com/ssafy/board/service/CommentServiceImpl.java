package com.ssafy.board.service;

import com.ssafy.board.entity.Comment;
import com.ssafy.board.entity.Post;
import com.ssafy.board.model.CommentDto;
import com.ssafy.board.repository.CommentRepository;
import com.ssafy.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService{
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    @Override
    public String addComment(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId()).get();
        Comment comment = commentDto.toEntity();

        comment.setPost(post);
        log.info(comment.toString());
        try{
            commentRepository.save(comment);
        } catch (Exception e){
            throw e;
        }
        return null;
    }

    @Override
    public void deleteComment(Long commentId) {
        try{
            commentRepository.deleteById(commentId);
        } catch (Exception e){
            throw e;
        }
    }
}
