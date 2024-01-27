# syntax=docker/dockerfile:1
# Stage 1: build the application
FROM maven:3.9.6-amazoncorretto-21 as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
RUN mvn verify --fail-never
ADD . $HOME
RUN mvn -f pom.xml clean package -DskipTests

# Stage 2: run the application
FROM amazoncorretto:21-alpine-jdk as run
COPY --from=build /usr/app/target/resort-service.jar /app/resort-service.jar
EXPOSE 8001
ENTRYPOINT ["java", "-jar", "/app/resort-service.jar"]

