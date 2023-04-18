package spring.newsSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.newsSite.entity.Role;

import java.util.Optional;

public interface RoleReposiotry extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
