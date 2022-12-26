## Домашнее задание 4
#### Сделать запуск программы тестирования студентов с помощью Spring Shell, провести дополнительную локализацию, написать тесты с помощью @SpringBootTest

Сборка:
````
mvn clean package
````

Запуск из командной строки:
````
cd target
java -Dfile.encoding=UTF-8 -jar hw-004-1.0-SNAPSHOT.jar
````
или
````
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dfile.encoding=UTF-8"
````
Использование shell:
````
register - для регистрации номера зачетной книжки
exam - для проведения экзамена
language langCode [countryCode] - для переключения языков
                                  пример: language en
                                          language ru RU  
````
