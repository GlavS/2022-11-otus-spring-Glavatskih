package ru.glavs.hw006.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long id;
    @Column(name = "text", nullable = false, length = 10_000)
    private String text;
    @Column(name = "author_nick", length = 30)
    private String authorNick;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "book_id")
    @ManyToOne
    private Book commentedBook;

    public Comment(String text, String authorNick, Date date, Book commentedBook) {
        this.text = text;
        this.authorNick = authorNick;
        this.date = date;
        this.commentedBook = commentedBook;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comment comment = (Comment) o;
        return id > 0 && Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
