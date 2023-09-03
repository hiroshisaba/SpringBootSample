FROM gradle:8.3.0-jdk8 AS build
COPY --chown=gradle:gradle ./ /home/gradle/src/
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:8
EXPOSE 8080
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar ./spring-boot-sample.jar
ENTRYPOINT ["java", "-jar", "spring-boot-sample.jar"]