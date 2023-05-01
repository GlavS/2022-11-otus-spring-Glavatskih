## Домашнее задание 15
#### Spring Integration

В приложении реализована интеграция независимых сервисов эндпойнтами типа ServiceActivator. 

Сборка:
````
mvn -Dmaven.test.skip=true clean package
````

Запуск из командной строки:
````
cd target
java -Dfile.encoding=UTF-8 -jar hw-015-1.0-SNAPSHOT.jar
````
или
````
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dfile.encoding=UTF-8"
````
