FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ENV JAVA_OPTS=""
ENV SPRING_PROFILES_ACTIVE=""
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "-Dspring.profiles.active=$SPRING_PROFILES_ACTIVE", "/app.jar"]
EXPOSE 8080