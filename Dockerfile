FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar

RUN addgroup -S demo && adduser -S demo -G demo
USER demo

WORKDIR /
VOLUME /tmp

COPY ${JAR_FILE} app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]
