package ru.glavs.hw013.security.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.glavs.hw013.security.entities.User;
import ru.glavs.hw013.security.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class DbUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No such user: " + username));

        return new SecurityUser(user);
    }
}
