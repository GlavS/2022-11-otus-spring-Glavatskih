package ru.glavs.hw012.security.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.glavs.hw012.security.entities.User;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;


    @Test
    void findByUsername() {
        String username = "test";
        User user = repository
                .findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("No such user: " + username));
        System.out.println(user);
    }
}