package ru.glavs.hw008.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.glavs.hw008.domain.Author;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("репозиторий авторов должен")
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Test
    @DisplayName("находить список авторов по списку их id")
    void shouldFindAllAuthorsByTheirIds() {
        List<Author> authorList = repository.findAll();
        List<String> ids = authorList.stream().map(Author::getId).collect(Collectors.toList());
        List<Author> authorsFoundByIds = repository.findAllByIdIn(ids);
        assertThat(authorsFoundByIds).usingRecursiveComparison().isEqualTo(authorList);
    }
}