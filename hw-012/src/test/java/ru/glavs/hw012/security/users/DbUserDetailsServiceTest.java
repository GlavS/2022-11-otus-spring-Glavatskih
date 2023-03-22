package ru.glavs.hw012.security.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import ru.glavs.hw012.security.entities.User;
import ru.glavs.hw012.security.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {DbUserDetailsService.class})
class DbUserDetailsServiceTest {

    @Autowired
    private DbUserDetailsService service;

    @MockBean
    private UserRepository repository;
    User user = new User(1L, "username", "password");

    @Test
    void loadUserByUsername() {
        when(repository.findByUsername("test")).thenReturn(Optional.of(user));
        assertThat(service.loadUserByUsername("test")).isNotNull().isInstanceOf(SecurityUser.class);
    }
}