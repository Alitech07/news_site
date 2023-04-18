package spring.newsSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.newsSite.entity.Post;
import spring.newsSite.payload.ApiResponse;
import spring.newsSite.payload.PostDto;
import spring.newsSite.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public List<Post> getPostsService(){
        return postRepository.findAll();
    }

    public Post getPostService(Integer id){
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }

    public ApiResponse addPostService(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setBody(postDto.getBody());
        post.setUrl(postDto.getUrl());
        postRepository.save(post);
        return new ApiResponse("Post saqlandi.",true);
    }
    public ApiResponse editPostService(Integer id,PostDto postDto){
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent()) return new ApiResponse("Bunday post mavjud emas.",false);
        Post post = optionalPost.get();
        post.setTitle(postDto.getTitle());
        post.setBody(postDto.getBody());
        post.setUrl(postDto.getUrl());
        postRepository.save(post);
        return new ApiResponse("Post tahrirlandi.",true);
    }

    public ApiResponse deletePostService(Integer id){
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent()) return new ApiResponse("Bunday post yuq.",false);
        postRepository.deleteById(id);
        return new ApiResponse("Post o'chirildi.",true);
    }
}
