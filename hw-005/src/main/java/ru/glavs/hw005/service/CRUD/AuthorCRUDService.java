package ru.glavs.hw005.service.CRUD;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.dao.AuthorDao;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.io.IOService;
import ru.glavs.hw005.service.display.DisplayService;

import java.util.List;

@Service
public class AuthorCRUDService {
    private final AuthorDao authorDao;
    private final IOService ioService;
    private final DisplayService<Author> authorDisplayService;

    public AuthorCRUDService(AuthorDao authorDao, IOService ioService, DisplayService<Author> authorDisplayService) {
        this.authorDao = authorDao;
        this.ioService = ioService;
        this.authorDisplayService = authorDisplayService;
    }

    public Author create(){
        String surname = ioService.readStringWithPrompt("Please enter surname: ");
        String name = ioService.readStringWithPrompt("Please enter name: ");
        String initials = ioService.readStringWithPrompt("Please enter initials: ");
        int id = authorDao.insertNew(name, surname, initials);
        return new Author(id, name, surname, initials);
    }
    public List<Author> searchBySurname(String surname){
        return authorDao.searchBySurname(surname);
    }
    public void printList(List<Author> authorList){
        authorDisplayService.printList(authorList);
    }

    public Author getById(int id) {
        return authorDao.getById(id);
    }
}
