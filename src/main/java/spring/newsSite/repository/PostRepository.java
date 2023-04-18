package spring.newsSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.newsSite.entity.Post;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
