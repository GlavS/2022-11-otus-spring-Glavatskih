## Домашнее задание 14
#### Spring Batch. Конвертация содержимого RDBMS в MongoDB

Сборка:
````
mvn -Dmaven.test.skip=true clean package
````

Запуск из командной строки:
````
cd target
java -Dfile.encoding=UTF-8 -jar hw-014-1.0-SNAPSHOT.jar
````
или
````
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dfile.encoding=UTF-8"
````
