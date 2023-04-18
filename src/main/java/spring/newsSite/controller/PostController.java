package spring.newsSite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Http2;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spring.newsSite.entity.Post;
import spring.newsSite.payload.ApiResponse;
import spring.newsSite.payload.PostDto;
import spring.newsSite.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping
    public HttpEntity<?> getPosts(){
        List<Post> postsService = postService.getPostsService();
        return ResponseEntity.status(200).body(postsService);
    }

    @PreAuthorize(value = "hasRole('ADD_POST')")
    @PostMapping("/add")
    public HttpEntity<?> addPost(@RequestBody PostDto postDto){
        ApiResponse apiResponse = postService.addPostService(postDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasRole('DELETE_POST')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deletePost(@PathVariable Integer id){
        ApiResponse apiResponse = postService.deletePostService(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
