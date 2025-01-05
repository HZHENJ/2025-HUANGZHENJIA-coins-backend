# using Maven to build proj
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# run
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/proj-1.0-SNAPSHOT.jar app.jar
COPY src/main/resources/config.yml /app/config.yml
EXPOSE 8080
CMD ["java", "-jar", "app.jar", "server", "config.yml"]
