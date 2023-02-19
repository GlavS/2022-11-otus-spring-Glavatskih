INSERT INTO AUTHORS (NAME, SURNAME, INITIALS)
VALUES ('Айзек', 'Азимов', 'А.'),
       ('Клиффорд', 'Саймак', 'К.'),
       ('Лев Николаевич', 'Толстой', 'Л.Н.'),
       ('Федор Михайлович', 'Достоевский', 'Ф.М.'),
       ('Антон Павлович', 'Чехов', 'А.П.'),
       ('Астрид', 'Линдгрен', 'А.'),
       ('Ганс Христиан', 'Андерсен', 'Г.Х.'),
       ('Артур', 'Конан-Дойл', 'А.'),
       ('Стивен', 'Кинг', 'С.'),
       ('Алексей Николаевич', 'Толстой', 'А.Н.');
INSERT INTO AUTHORS (NAME, SURNAME, INITIALS)
VALUES ('Гилберт Кейз', 'Честертон', 'Г.К.'),
       ('Владислав', 'Ходасевич', 'В.'),
       ('Рэй', 'Бредбери', 'Р.');

INSERT INTO GENRES (NAME)
VALUES ('Детектив'),
       ('Фантастика'),
       ('Сказка'),
       ('Классика'),
       ('Хоррор'),
       ('Исторический'),
       ('Публицистика');

INSERT INTO BOOKS (AUTHOR, GENRE, TITLE)
VALUES (1, 2, 'Я, робот'),
       (1, 2, 'Основания'),
       (2, 2, 'Почти как люди'),
       (3, 4, 'Война и мир'),
       (3, 4, 'Анна Каренина'),
       (3, 4, 'Воскресение'),
       (4, 4, 'Преступление и наказание'),
       (4, 4, 'Идиот'),
       (4, 4, 'История села Степанчиково'),
       (5, 4, 'Вишневый сад');
INSERT INTO BOOKS (AUTHOR, GENRE, TITLE)
VALUES (2, 2, 'Заповедник гоблинов'),
       (6, 3, 'Малыш и Карлсон'),
       (6, 3, 'Пеппи Длинныйчулок'),
       (7, 3, 'Дюймовочка'),
       (7, 3, 'Снежная королева'),
       (7, 3, 'Тень'),
       (8, 1, 'Приключения Шерлока Холмса'),
       (8, 1, 'Торговый дом Гердлстон'),
       (8, 2, 'Марракотова бездна'),
       (9, 5, 'Мертвая зона');
INSERT INTO BOOKS (AUTHOR, GENRE, TITLE)
VALUES (9, 5, 'Сияние'),
       (10, 6, 'Петр Первый'),
       (11, 7, 'Франциск Ассизский'),
       (11, 1, 'Приключения патера Брауна'),
       (11, 4, 'Человек, который был четвергом'),
       (12, 7, 'Некрополь'),
       (12, 6, 'Белый коридор'),
       (13, 2, 'Марсианские хроники'),
       (13, 2, '451 градус по Фаренгейту');
INSERT INTO PUBLIC.COMMENTS (TEXT, AUTHOR_NICK, "DATE", BOOK_ID)
VALUES ('Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis cumque debitis earum inventore nemo nisi, repudiandae! Ab assumenda, autem consequatur corporis cum cumque cupiditate deleniti dignissimos dolor dolorum eaque, eveniet excepturi facilis in ipsam itaque iusto modi nam natus neque nesciunt numquam officia quas quasi reprehenderit sed temporibus voluptate? Accusantium, beatae dolore et explicabo in incidunt magni quod. Animi aperiam commodi, consectetur deleniti dicta dolore dolorem ea earum enim expedita explicabo fugit id illo ipsa iusto laudantium maiores modi necessitatibus neque nihil nostrum odit officiis quae quidem quos ratione reiciendis rem saepe sed unde ut vel, velit voluptates? Aliquid hic odio odit sequi voluptatem. Ab aliquid at deserunt et expedita harum, id illum impedit iste mollitia nihil quia voluptas? Alias amet architecto aspernatur aut deleniti deserunt dolor dolores est in laborum molestiae, pariatur quam, reprehenderit repudiandae rerum sint ullam! Alias aliquid architecto eum, exercitationem in iste iure neque nihil quod sapiente? Adipisci amet dicta ea eius est excepturi exercitationem, laborum minus molestiae nesciunt, pariatur perferendis qui quo repudiandae voluptates. A dolor eum eveniet ipsum maiores quos sit voluptatem? Aspernatur dolorum eligendi error labore placeat. Accusantium adipisci architecto eum nihil pariatur. Animi assumenda cum, illo laboriosam quam repudiandae vitae. Ea, molestias.', 'reader', '2023-01-19', 1),
       ('Отличная книга про роботов', 'goodReader', '2023-01-13', 1);


-- Потрясающая книга