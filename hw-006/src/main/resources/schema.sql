DROP TABLE IF EXISTS BOOKS CASCADE ;
DROP TABLE IF EXISTS GENRES CASCADE ;
DROP TABLE IF EXISTS AUTHORS CASCADE ;
DROP TABLE IF EXISTS COMMENTS CASCADE ;

CREATE TABLE books
(
    book_id INT AUTO_INCREMENT NOT NULL,
    author  INT                NOT NULL,
    genre   INT                NOT NULL,
    title   VARCHAR(255)       NOT NULL,
    CONSTRAINT pk_books PRIMARY KEY (book_id)
);

CREATE TABLE authors
(
    author_id INT AUTO_INCREMENT NOT NULL,
    name      VARCHAR(30)        NOT NULL,
    surname   VARCHAR(30)        NOT NULL,
    initials  VARCHAR(4),
    CONSTRAINT pk_authors PRIMARY KEY (author_id)
);

CREATE TABLE genres
(
    genre_id INT AUTO_INCREMENT NOT NULL,
    genre    VARCHAR(30)        NOT NULL,
    CONSTRAINT pk_genres PRIMARY KEY (genre_id)
);

CREATE TABLE comments
(
    comment_id  INT AUTO_INCREMENT NOT NULL,
    text        VARCHAR(10000)     NOT NULL,
    author_nick VARCHAR(30),
    date        TIMESTAMP,
    book_id    INT,
    CONSTRAINT pk_comments PRIMARY KEY (comment_id)
);

ALTER TABLE books
    ADD CONSTRAINT FK_BOOKS_ON_AUTHOR FOREIGN KEY (author)
        REFERENCES authors (author_id)ON DELETE CASCADE;
ALTER TABLE books
    ADD CONSTRAINT FK_BOOKS_ON_GENRE FOREIGN KEY (genre)
        REFERENCES genres (genre_id) ON DELETE CASCADE;
ALTER TABLE comments
    ADD CONSTRAINT FK_COMMENTS_ON_COMMENTS FOREIGN KEY (book_id)
        REFERENCES books (book_id) ON DELETE CASCADE;

CREATE UNIQUE INDEX uq_authors
    ON AUTHORS(name, surname, initials);

ALTER TABLE genres
    ADD CONSTRAINT uc_genres_genre UNIQUE (genre);


