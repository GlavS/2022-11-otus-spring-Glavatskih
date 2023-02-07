## Домашнее задание 8
#### Доменная модель "Книги-Авторы-Жанры". Перенос CRUD-сервиса на SpringData MongoDB.

Сборка:
````
mvn -Dmaven.test.skip=true clean package
````

Запуск из командной строки:
````
cd target
java -Dfile.encoding=UTF-8 -jar hw-008-1.0-SNAPSHOT.jar
````
или
````
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dfile.encoding=UTF-8"
````
Использование shell:
````
       01. Book CRUD
       console: Show H2 console. (under Linux run web browser first)
       show: Show one book.
       update: Update book.
       create: Create new book.
       list: List all books (with comments only - with "c" option).
       delete: Delete book.

       02. Comment CRUD
       comment-delete: Delete existing book comment.
       comment-add: Add comment to book.
       comments-show: Show comments by book.
       
       Обратить внимание, что в терминалах Windows наболюдаются проблемы с вводом кириллицы.
````