FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod","-jar","/app.jar"]

/Users/victoraldir/dev/projects/soundtracker/soundtracker/build/libs/soundtracker-0.0.1-SNAPSHOT.jar