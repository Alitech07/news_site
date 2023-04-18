package spring.newsSite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spring.newsSite.entity.Comment;
import spring.newsSite.entity.Post;
import spring.newsSite.payload.ApiResponse;
import spring.newsSite.payload.CommentDto;
import spring.newsSite.payload.PostDto;
import spring.newsSite.service.CommentService;
import spring.newsSite.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping
    public HttpEntity<?> getComments(){
        List<Comment> comments = commentService.getCommentsService();
        return ResponseEntity.status(200).body(comments);
    }

    @PreAuthorize(value = "hasRole('ADD_COMMENT')")
    @PostMapping("/add")
    public HttpEntity<?> addComment(@RequestBody CommentDto commentDto){
        ApiResponse apiResponse = commentService.addCommentService(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasRole('EDIT_COMMENT')")
    @PostMapping("/edit/{id}")
    public HttpEntity<?> editComment(@RequestBody CommentDto commentDto,@PathVariable Integer id){
        ApiResponse apiResponse = commentService.editCommentService(commentDto,id);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }


    @PreAuthorize(value = "hasRole('DELETE_COMMENT')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteComment(@PathVariable Integer id){
        ApiResponse apiResponse = commentService.deleteCommentService(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
