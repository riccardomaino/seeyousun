# syntax=docker/dockerfile:1
# Stage 1: build the application
FROM maven:3.9.6-amazoncorretto-21 as build
WORKDIR /usr/app
COPY pom.xml .
RUN mvn verify --fail-never
COPY . .
RUN mvn -f pom.xml clean package -DskipTests

# Stage 2: run the application
FROM amazoncorretto:21-alpine-jdk as run
COPY --from=build /usr/app/target/resort-service.jar /app/resort-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/resort-service.jar"]

