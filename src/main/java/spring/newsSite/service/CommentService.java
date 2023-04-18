package spring.newsSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.newsSite.entity.Comment;
import spring.newsSite.entity.Post;
import spring.newsSite.payload.ApiResponse;
import spring.newsSite.payload.CommentDto;
import spring.newsSite.payload.PostDto;
import spring.newsSite.repository.CommentRepository;
import spring.newsSite.repository.PostRepository;
import sun.java2d.pipe.AAShapePipe;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    public List<Comment> getCommentsService(){
        return commentRepository.findAll();
    }
    public List<Comment> getPostCommentsService(Long postId){
        List<Comment> postComments = commentRepository.getCommentsByPostId(postId);
        return postComments;
    }

    public ApiResponse addCommentService(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setText(commentDto.getText());

        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (!optionalPost.isPresent()) return new ApiResponse("Bunday post mavjud emas.",false);
        comment.setPost(optionalPost.get());
        return new ApiResponse("Comment qo'shildi.",true);
    }

    public ApiResponse editCommentService(CommentDto commentDto,Integer id){
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent()) return new ApiResponse("Bunday izoh mavjud emas.",false);

        Comment comment = optionalComment.get();
        comment.setText(commentDto.getText());

        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (!optionalPost.isPresent()) return new ApiResponse("Bunday post mavjud emas.",false);
        comment.setPost(optionalPost.get());
        return new ApiResponse("Izohingiz tahrirlandi.",true);
    }

    public ApiResponse deleteCommentService(Integer id){
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent()) return new ApiResponse("Bunday comment yuq.",false);
        commentRepository.deleteById(id);
        return new ApiResponse("Izoh o'chirildi.",true);
    }
}
