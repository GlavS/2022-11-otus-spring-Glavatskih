## Домашнее задание 13
#### Авторизация на Spring Security

Сборка:
````
mvn clean package
````

Запуск из командной строки:
````
cd target
java -Dfile.encoding=UTF-8 -jar hw-013-1.0-SNAPSHOT.jar
````
или
````
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dfile.encoding=UTF-8"
````
- В адресной строке браузера ввести: http://localhost:8080
- В системе два пользователя: 
   - admin, пароль: password, роль: ADMIN
   - user, пароль: userpass, роль: USER