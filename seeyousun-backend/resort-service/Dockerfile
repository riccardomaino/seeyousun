# syntax=docker/dockerfile:1
# Stage 1: build the application
FROM maven:3.9.6-amazoncorretto-21 as build
WORKDIR /resort-service
COPY . .
RUN mvn -f pom.xml clean package -DskipTests

# Stage 2: run the application
FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
COPY --from=build /resort-service/target/resort-service.jar /app/
EXPOSE 8001
ENTRYPOINT ["java", "-jar", "resort-service.jar"]


