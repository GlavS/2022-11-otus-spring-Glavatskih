package ru.glavs.hw013.security.users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import ru.glavs.hw013.security.entities.User;
import ru.glavs.hw013.security.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {DbUserDetailsService.class})
@DisplayName("В сервисе DbUserDetailsService")
class DbUserDetailsServiceTest {

    @Autowired
    private DbUserDetailsService service;

    @MockBean
    private UserRepository repository;


    @Test
    @DisplayName("метод loadUserByUsername должен возвращать объект типа UserDetails")
    void loadUserByUsernameShouldReturnExpectedUserDetailsObject() {
        User user = new User(1L, "username", "password");
        when(repository.findByUsername("test")).thenReturn(Optional.of(user));
        assertThat(service.loadUserByUsername("test")).isNotNull().isInstanceOf(SecurityUser.class);
    }
}