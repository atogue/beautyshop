# Generate Build
FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -Dmaven.test.skip=true

# Dockerize
FROM openjdk:17.0.2-slim
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY --from=build /app/target/beauty-shop-0.0.1-SNAPSHOT.jar ./beauty-shop-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","beauty-shop-0.0.1.jar"]