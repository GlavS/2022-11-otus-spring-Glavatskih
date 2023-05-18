package ru.glavs.hw017.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @ToString.Exclude
    @JoinColumn(name = "book_id")
    @ManyToOne(fetch = FetchType.LAZY)
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
