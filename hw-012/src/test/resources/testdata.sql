
INSERT INTO AUTHORS (NAME, SURNAME, INITIALS)
VALUES ('Имя1', 'Фамилия1', 'А.А.'),
       ('Имя2', 'Фамилия2', 'Б.Б.');

INSERT INTO GENRES (NAME)
VALUES ('Жанр1'),
       ('Жанр2');

INSERT INTO BOOKS (AUTHOR, GENRE, TITLE)
VALUES (1, 1, 'Книга1'),
       (1, 1, 'Книга2'),
       (2, 1, 'Книга3'),
       (2, 2, 'Книга4');

INSERT INTO COMMENTS ("TEXT", AUTHOR_NICK, "DATE", BOOK_ID)
VALUES ('Comment1, comment1 comment1 comment1 comment1.', 'commentator1', '2023-01-19', 1),
       ('Comment2, comment2 comment2 comment2 comment2.', 'commentator2', '2023-01-17', 1),
       ('Comment3, comment3 comment3 comment3 comment3.', 'commentator3', '2023-01-17', 1),
       ('Comment4, comment4 comment4 comment4 comment4.', 'commentator4', '2023-01-17', 1);

INSERT INTO USERS (USERNAME, PASSWORD)
values ('test', '{bcrypt}$2a$10$JZLu6DH0T/r.IWf7X5fkS.zlC8rsRF818YFQfMcX8uXObfMUtVyE.'); --"test"
