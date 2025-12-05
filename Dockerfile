FROM openjdk:17.0.2-slim
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY target/beauty-shop-0.0.1-SNAPSHOT.jar /usr/src/app/beauty-shop-0.0.1.jar
ENTRYPOINT ["java","-jar","beauty-shop-0.0.1.jar"]
EXPOSE 8080