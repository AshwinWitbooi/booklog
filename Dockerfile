FROM openjdk:8-jdk-alpine
COPY ARG JAR_FILE=target/*.jar app.jar
ENTRYPOINT ["java","-Djasypt.encryptor.password=booklog","-jar","/app.jar"]