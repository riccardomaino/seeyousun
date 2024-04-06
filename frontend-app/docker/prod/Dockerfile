# syntax=docker/dockerfile:1
# Stage 1: Build the Angular application
FROM node:latest AS build
WORKDIR /usr/app
COPY ../../package.json .
COPY ../../package-lock.json .
# Install dependencies
RUN npm install --force
# Copy the rest of the application
COPY ../.. .
# Build the Angular application
RUN npm run build --configuration see-you-sun

# Stage 2: Run the Angular application
FROM nginx:latest AS run
COPY --from=build /usr/app/dist/see-you-sun /usr/share/nginx/html
COPY ../../nginx.conf /etc/nginx/conf.d/default.conf
# Expose port 80
EXPOSE 80