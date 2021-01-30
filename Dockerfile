FROM openjdk:8-jdk-alpine
COPY /target/*.jar app.jar
ENTRYPOINT ["java","-Djasypt.encryptor.password=booklog","-Dspring.profiles.active=prod","-jar","/app.jar"]