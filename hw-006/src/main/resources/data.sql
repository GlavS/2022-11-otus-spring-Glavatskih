INSERT INTO BOOKS (AUTHOR_ID, GENRE_ID, TITLE)
VALUES (1, 2, 'Я, робот'),
       (1, 2, 'Основания'),
       (2, 2, 'Почти как люди'),
       (3, 4, 'Война и мир'),
       (3, 4, 'Анна Каренина'),
       (3, 4, 'Воскресенье'),
       (4, 4, 'Преступление и наказание'),
       (4, 4, 'Идиот'),
       (4, 4, 'История села Степанчиково'),
       (5, 4, 'Вишневый сад');
INSERT INTO BOOKS (AUTHOR_ID, GENRE_ID, TITLE)
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
INSERT INTO BOOKS (AUTHOR_ID, GENRE_ID, TITLE)
VALUES (9, 5, 'Сияние'),
       (10, 6, 'Петр Первый'),
       (11, 7, 'Франциск Ассизский'),
       (11, 1, 'Приключения патера Брауна'),
       (11, 4, 'Человек, который был четвергом'),
       (12, 7, 'Некрополь'),
       (12, 6, 'Белый коридор'),
       (13, 2, 'Марсианские хроники'),
       (13, 2, '451 градус по Фаренгейту');

INSERT INTO GENRES (GENRE)
VALUES ('Детектив'),
       ('Фантастика'),
       ('Сказка'),
       ('Классика'),
       ('Хоррор'),
       ('Исторический'),
       ('Публицистика');


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
