//package ru.glavs.hw008.repository;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import ru.glavs.hw008.domain.Genre;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataMongoTest
//@DisplayName("Репозиторий жанров должен")
//class GenreRepositoryTest {
//    @Autowired
//    private GenreRepository repository;
//
//    @Test
//    @DisplayName("находить жанр по фрагменту названия")
//    void shouldFindGenreByNameContainingTextFragmentIgnoreCase() {
//        List<Genre> genreList = repository.findByNameContainingIgnoreCase("genre1");
//        assertThat(genreList).hasSize(1);
//        genreList = repository.findByNameContainingIgnoreCase("genre");
//        assertThat(genreList).hasSize(2);
//    }
//
//    @Test
//    @DisplayName("находить список жанов по списку Id")
//    void shouldFindListOfGenresByListOfId(){
//        List<Genre> genreList = repository.findAll();
//        List<String> ids = genreList.stream().map(Genre::getId).collect(Collectors.toList());
//        List<Genre> genreListByIds = repository.findAllByIdIn(ids);
//        assertThat(genreListByIds).usingRecursiveComparison().isEqualTo(genreList);
//    }
//
//}