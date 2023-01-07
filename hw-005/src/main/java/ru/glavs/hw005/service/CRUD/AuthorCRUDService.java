package ru.glavs.hw005.service.CRUD;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.dao.AuthorDao;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.io.IOService;

import java.util.List;

@Service
public class AuthorCRUDService {
    private final AuthorDao authorDao;
    private final IOService ioService;

    public AuthorCRUDService(AuthorDao authorDao, IOService ioService) {
        this.authorDao = authorDao;
        this.ioService = ioService;
    }

    public void create(){
        String surname = ioService.readStringWithPrompt("Please enter surname: ");
        String name = ioService.readStringWithPrompt("Please enter name: ");
        String initials = ioService.readStringWithPrompt("Please enter initials: ");
        authorDao.insertNew(name, surname, initials);
    }
    public List<Author> searchBySurname(String surname){
        return authorDao.searchBySurname(surname);
    }

}
