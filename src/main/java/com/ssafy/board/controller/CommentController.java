package com.ssafy.board.controller;

import com.ssafy.board.model.CommentDto;
import com.ssafy.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping
    public ResponseEntity<String> addComment(@RequestBody CommentDto commentDto){
        commentService.addComment(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{comment-id}")
    public ResponseEntity<Void> deleteComment(@PathVariable(name = "comment-id") Long commentId){
        try{
            commentService.deleteComment(commentId);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
