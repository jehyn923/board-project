package com.ssafy.board.service;

import com.ssafy.board.entity.Post;
import com.ssafy.board.model.CommentResponseDto;
import com.ssafy.board.model.PostCommentDto;
import com.ssafy.board.model.PostDto;
import com.ssafy.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public String savePost(PostDto postDto) {
        try{
            postRepository.save(postDto.toEntity());
        } catch (Exception e){
            throw e;
        }
        return "Success";
    }

    @Override
    public Page<PostDto> getPostList(Pageable pageable) {
        Page<Post> posts= postRepository.findAll(pageable);
        return new PageImpl<>(posts.get().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList()),posts.getPageable(),posts.getTotalElements());
    }

    @Override
    public PostCommentDto getPost(Long postId) {
        Post post = postRepository.findById(postId).get();
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return PostCommentDto.builder().postDto(postDto)
                .commentList(post.getComments().stream().map(comment -> modelMapper.map(comment, CommentResponseDto.class)).collect(Collectors.toList()))
                .build();
    }

    @Override
    public PostCommentDto updatePost(PostDto postDto) {
        Post post = postRepository.findById(postDto.getId()).get();
        post = post.updatePost(postDto.toEntity());

        try{
            post = postRepository.save(post);
        }catch (Exception e){
            throw e;
        }
        postDto = modelMapper.map(post,PostDto.class);

        return PostCommentDto.builder().postDto(postDto)
                .commentList(post.getComments().stream().map(comment -> modelMapper.map(comment,CommentResponseDto.class)).collect(Collectors.toList()))
                .build();
    }

    @Override
    public void deletePost(Long postId) {
        try{
            postRepository.deleteById(postId);
        }catch (Exception e){
            throw e;
        }
    }
}
