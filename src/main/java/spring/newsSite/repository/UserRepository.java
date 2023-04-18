package spring.newsSite.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.newsSite.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
