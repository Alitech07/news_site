package spring.newsSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.newsSite.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> getCommentsByPostId(Long post_id);
}
