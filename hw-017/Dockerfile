FROM bellsoft/liberica-openjdk-alpine:20.0.1-10
COPY target/hw-017-1.0-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "/app/app.jar"]