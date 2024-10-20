# First stage: build the application
FROM maven:3.9-eclipse-temurin-21-alpine AS build
COPY . /app
WORKDIR /app
RUN mvn -e clean package -DskipTests

RUN apk update && apk add --no-cache wget
RUN wget -O pic.png https://www.mirea.ru/upload/medialibrary/80f/MIREA_Gerb_Colour.png

LABEL maintainer="Matrosov Danil IKBO-01-21"
ONBUILD RUN echo "Автор Матросов Данил Ярославович"

# Second stage: create a slim image
FROM openjdk:21-slim

WORKDIR /app

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://danil:5432/postgres
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

COPY --from=build /app/pic.png ./public/pic.png
COPY --from=build /app/target/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

#docker network create myapp-network
#docker build -t crud .\TVKSP_3prac\
#docker run --network myapp-network -p 8080:8080 -d crud
#docker run -p 5432:5432 --network myapp-network --name danil -e POSTGRES_PASSWORD=postgres -d postgres