package ru.glavs.hw008.service.ui;

import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.Author;
import ru.glavs.hw008.io.IOService;
import ru.glavs.hw008.service.CRUD.AuthorCRUD;
import ru.glavs.hw008.service.view.AbstractViewService;

import java.util.ArrayList;
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
    public List<Author> requestAuthors(String surname) {
        List<Author> authorsList = new ArrayList<>();
        List<Author> searchResultList = authorCRUDService.searchBySurname(surname);
        if (searchResultList == null) {
            String answer = ioService.readStringWithPrompt("No such author in database. Do you want to create one? (y/n): ");
            if (answer.equalsIgnoreCase("y")) {
                authorsList.addAll(createAuthors());
            } else {
                authorsList.addAll(pickAuthorsFrom(authorCRUDService.findAll()));
            }

        } else if (searchResultList.size() > 1) {
            authorsList.addAll(pickAuthorsFrom(searchResultList));
        } else {
            authorsList.add(searchResultList.get(0));
        }
        return authorsList;
    }

    @Override
    public List<Author> createAuthors() {
        List<Author> result = new ArrayList<>();
        String answer;
        do {
            Author author = new Author();
            author.setSurname(ioService.readStringWithPrompt("Enter author's surname: "));
            author.setName(ioService.readStringWithPrompt("Enter author's name: "));
            author.setInitials(ioService.readStringWithPrompt("Enter initials: "));
            result.add(author);
            answer = ioService.readStringWithPrompt("Do you want to create more(y/n)?");
        } while (!answer.equalsIgnoreCase("n"));
        return result;
    }

    @Override
    public List<Author> pickAuthorsFrom(List<Author> authorList) {
        List<Author> result = new ArrayList<>();
        authorList.sort(new AuthorSort());
        String answer;
        do {
            displayService.printList(authorList);
            long authorId = ioService.readIntWithPrompt("Please enter desired author id: ");
            result.add(authorCRUDService.findById(authorId));
            answer = ioService.readStringWithPrompt("Do you want to create more(y/n)?");
        } while (!answer.equalsIgnoreCase("n"));
        return result;
    }

    private static class AuthorSort implements Comparator<Author> {
        @Override
        public int compare(Author o1, Author o2) {
            return o1.getSurname().compareTo(o2.getSurname());
        }
    }
}
