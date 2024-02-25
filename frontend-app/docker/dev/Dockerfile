# syntax=docker/dockerfile:1
# Stage 1: Build the Angular application
FROM node:latest AS build
RUN npm install -g @angular/cli
WORKDIR /usr/app
COPY ../../package.json .
COPY ../../package-lock.json .
# Install dependencies
RUN npm install --force
# Copy the rest of the application
COPY ../.. .
# Build the application
EXPOSE 4200
CMD["ng", "serve", "--host", "0.0.0.0", "--poll", "200"]

