package ru.glavs.hw016.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.glavs.hw016.dao.BookDao;
import ru.glavs.hw016.domain.Book;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DostoevskyHealthCheck implements HealthIndicator {

    private final BookDao bookDao;

    @Override
    public Health health() {
        List<Book> bookList = bookDao.findByAuthorSurname("Достоевский");
        if (bookList.size() >= 3) {
            return Health
                    .status("UP")
                    .withDetail("Достоевский", "Достоевского вдоволь")
                    .build();
        } else if (bookList.size() > 0) {
            return Health
                    .status("UP")
                    .withDetail("Достоевский", "Достоевский заканчивается!")
                    .build();
        } else {
            return Health
                    .status("DOWN")
                    .withDetail("Достоевский", "Достоевский нужен, как воздух!!!")
                    .build();
        }

    }

    @Override
    public Health getHealth(boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }
}
