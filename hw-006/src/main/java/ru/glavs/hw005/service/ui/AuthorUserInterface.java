package ru.glavs.hw005.service.ui;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.io.IOService;
import ru.glavs.hw005.service.CRUD.AuthorCRUD;
import ru.glavs.hw005.service.display.AbstractDisplayService;

import java.util.List;

@Service
public class AuthorUserInterface {
    private final AuthorCRUD authorCRUDService;
    private final IOService ioService;
    private final AbstractDisplayService<Author> displayService;

    public AuthorUserInterface(AuthorCRUD authorCRUDService,
                               IOService ioService,
                               AbstractDisplayService<Author> displayService) {
        this.authorCRUDService = authorCRUDService;
        this.ioService = ioService;
        this.displayService = displayService;
    }

    public Author requestAuthor(String surname) {
        Author author;
        List<Author> searchResultList = authorCRUDService.searchBySurname(surname);
        if (searchResultList.size() == 0) {
            String answer = ioService.readStringWithPrompt("No such author in database. Do you want to create one? (y/n): ");
            if (answer.equalsIgnoreCase("y")) {
                author = createAuthor();
            } else {
                author = pickAuthorFrom(authorCRUDService.findAll());
            }

        } else if (searchResultList.size() > 1) {
            author = pickAuthorFrom(searchResultList);
        } else {
            author = searchResultList.get(0);
        }
        return author;
    }

    public Author createAuthor() {
        Author author = new Author();
        author.setSurname(ioService.readStringWithPrompt("Enter author's surname: "));
        author.setName(ioService.readStringWithPrompt("Enter author's name: "));
        author.setInitials(ioService.readStringWithPrompt("Enter initials: "));
        return authorCRUDService.save(author);
    }

    public Author pickAuthorFrom(List<Author> authorList) {
        displayService.printList(authorList);
        long authorId = ioService.readIntWithPrompt("Please enter desired author id: ");
        return authorCRUDService.findById(authorId);
    }
}
