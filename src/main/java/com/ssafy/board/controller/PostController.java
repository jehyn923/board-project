package com.ssafy.board.controller;

import com.ssafy.board.model.PostCommentDto;
import com.ssafy.board.model.PostDto;
import com.ssafy.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto){
        log.info(postDto.toString());
        postService.savePost(postDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<PostDto>> getPostList(Pageable pageable){
        return new ResponseEntity<>(postService.getPostList(pageable), HttpStatus.OK);
    }
    @GetMapping(path = "/{post-id}")
    public ResponseEntity<PostCommentDto> getPost(@PathVariable(name = "post-id") Long postId){
        return new ResponseEntity<>(postService.getPost(postId),HttpStatus.OK);
    }

    @PutMapping(path = "/{post-id}")
    public ResponseEntity<PostCommentDto> updatePost(@PathVariable(name = "post-id") Long postId,
                                              @RequestBody PostDto postDto){
        PostCommentDto postCommentDto;
        postDto.setId(postId);
        try{
             postCommentDto = postService.updatePost(postDto);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(postCommentDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{post-id}")
    public ResponseEntity<Void> deletePost(@PathVariable(name = "post-id") Long postId){

        try{
           postService.deletePost(postId);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
