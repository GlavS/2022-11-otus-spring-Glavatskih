package ru.glavs.hw005.service.CRUD;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.dao.AuthorDao;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Book;
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

    public Author create() {
        String surname = ioService.readStringWithPrompt("Please enter surname: ");
        String name = ioService.readStringWithPrompt("Please enter name: ");
        String initials = ioService.readStringWithPrompt("Please enter initials: ");
        int id = authorDao.insertNew(name, surname, initials);
        Author result = new Author(id, name, surname, initials);
        authorDisplayService.printOne(result);
        ioService.println("New author written to DB");
        return result;
    }

    public List<Author> searchBySurname(String surname) {
        return authorDao.searchBySurname(surname);
    }

    public void printList(List<Author> authorList) {
        authorDisplayService.printList(authorList);
    }

    public Author getById(int id) {
        return authorDao.getById(id);
    }

    public Author getAuthorBySurname(String surname) {
        List<Author> supposedAuthorList = searchBySurname(surname);
        if (supposedAuthorList.size() == 0) {
            String answer = ioService.readStringWithPrompt("No such author in database, do you want to create one (y/n)?");
            if (answer.equalsIgnoreCase("y")) {
                return create();
            } else {
                ioService.println("Author creation aborted");
                return null;
            }
        } else {
            printList(supposedAuthorList);
            int id = ioService.readIntWithPrompt("Please indicate author's ID:");
            return getById(id);
        }
    }

    public Author getAuthorForUpdate(Book bookForUpdate) {
        Author result;
        String surname = ioService.readStringWithPrompt("Please enter new author's surname, or ENTER to skip:");
        if (!surname.equals("")) {
            if ((result = getAuthorBySurname(surname)) == null) {
                ioService.println("Author creation aborted");
                return null;
            }
        } else {
            result = bookForUpdate.getAuthor();
        }
        return result;
    }
}
