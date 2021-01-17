FROM openjdk:8-jdk-alpine
ARG APP
ARG JAR_FILE=target/${APP}.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djasypt.encryptor.password=booklog","-jar","/app.jar"]