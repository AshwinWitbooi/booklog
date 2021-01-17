FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar /usr/app/
COPY ${JAR_FILE} app.jar
WORKDIR /usr/app
ENTRYPOINT ["java","-Djasypt.encryptor.password=booklog","-jar","/app.jar"]