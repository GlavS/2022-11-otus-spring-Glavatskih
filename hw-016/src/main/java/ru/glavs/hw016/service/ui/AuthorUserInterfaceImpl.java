package ru.glavs.hw016.service.ui;

import org.springframework.stereotype.Service;
import ru.glavs.hw016.domain.Author;
import ru.glavs.hw016.io.IOService;
import ru.glavs.hw016.service.CRUD.AuthorCRUD;
import ru.glavs.hw016.service.view.AbstractViewService;

import java.util.Comparator;
import java.util.List;

@Service
public class AuthorUserInterfaceImpl implements AuthorUI {
    private final AuthorCRUD authorCRUDService;
    private final IOService ioService;
    private final AbstractViewService<Author> displayService;

    public AuthorUserInterfaceImpl(AuthorCRUD authorCRUDService,
                                   IOService ioService,
                                   AbstractViewService<Author> displayService) {
        this.authorCRUDService = authorCRUDService;
        this.ioService = ioService;
        this.displayService = displayService;
    }

    @Override
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

    @Override
    public Author createAuthor() {
        Author author = new Author();
        author.setSurname(ioService.readStringWithPrompt("Enter author's surname: "));
        author.setName(ioService.readStringWithPrompt("Enter author's name: "));
        author.setInitials(ioService.readStringWithPrompt("Enter initials: "));
        return author;
    }

    @Override
    public Author pickAuthorFrom(List<Author> authorList) {
        authorList.sort(new AuthorSort());
        displayService.printList(authorList);
        long authorId = ioService.readIntWithPrompt("Please enter desired author id: ");
        return authorCRUDService.findById(authorId);
    }
    private static class AuthorSort implements Comparator<Author>{
        @Override
        public int compare(Author o1, Author o2) {
            return o1.getSurname().compareTo(o2.getSurname());
        }
    }
}
