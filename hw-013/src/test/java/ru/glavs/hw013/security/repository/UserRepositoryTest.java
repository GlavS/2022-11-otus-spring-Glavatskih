package ru.glavs.hw013.security.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.glavs.hw013.security.entities.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Репозиторий пользователей должен")
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;


    @Test
    @DisplayName("находить пользователя по имени")
    void findByUsernameMethodShouldReturnExpectedUser() {
        String username = "test";
        Optional<User> user = repository
                .findByUsername(username);
        assertThat(user).isNotEmpty();
        assertThat(user.get()).usingRecursiveComparison().isInstanceOf(User.class);
    }
}