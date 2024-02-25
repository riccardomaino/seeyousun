# syntax=docker/dockerfile:1
# Stage 1: Build the Angular application
FROM node:21-alpine AS build
WORKDIR /usr/app
COPY package.json .
COPY package-lock.json .
# Install dependencies
RUN npm install --force
# Copy the rest of the application
COPY . .
# Build the Angular application
RUN npm run build

# Stage 2: Run the Angular application
FROM nginx:1.25-alpine-perl AS run
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d
COPY --from=build /usr/app/dist/see-you-sun /usr/share/nginx/html
# Expose port 4200
EXPOSE 4200