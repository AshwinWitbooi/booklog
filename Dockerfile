FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/*.jar app.jar
ENTRYPOINT ["java","-Djasypt.encryptor.password=booklog","-jar","/app.jar"]