## Домашнее задание 16
#### Spring Boot Actuator, healthcheck, logfile

Сборка:
````
mvn -Dmaven.test.skip=true clean package
````

Запуск из командной строки:
````
cd target
java -Dfile.encoding=UTF-8 -jar hw-016-1.0-SNAPSHOT.jar
````
или
````
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dfile.encoding=UTF-8"
````
Перейти по адресу: http://localhost:8080/actuator

Пользовательский healthcheck проверяет наличие в библиотеке сочинений Достоевского в достаточном количестве, и находится по адресу: http://localhost:8080/actuator/health/dostoevskyHealthCheck



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