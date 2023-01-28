package ru.glavs.hw005.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "books")
@NamedEntityGraph(name = "book-graph", attributeNodes = {
        @NamedAttributeNode("author"),
        @NamedAttributeNode("genre"),
        @NamedAttributeNode("title")
})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author")
    private Author author;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "genre")
    private Genre genre;
    @Column(name = "title", nullable = false)
    private String title;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "book_id")
    @Fetch(FetchMode.SUBSELECT)
    @ToString.Exclude
    private List<Comment> comments;

    public Book(Author author, Genre genre, String title) {
        this.author = author;
        this.genre = genre;
        this.title = title;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return id > 0 && Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
