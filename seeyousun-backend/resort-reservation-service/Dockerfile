# syntax=docker/dockerfile:1
# Stage 1: build the application
FROM maven:3.9.6-amazoncorretto-21 as build
WORKDIR /resort-reservation-service
COPY . .
RUN mvn -f pom.xml clean package -DskipTests

# Stage 2: run the application
FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
COPY --from=build /resort-reservation-service/target/resort-reservation-service.jar /app/
EXPOSE 8001
ENTRYPOINT ["java", "-jar", "resort-reservation-service.jar"]


