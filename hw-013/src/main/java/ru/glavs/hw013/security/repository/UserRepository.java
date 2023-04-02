package ru.glavs.hw013.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glavs.hw013.security.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
