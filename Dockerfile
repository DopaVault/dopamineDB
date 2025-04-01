# Use an official OpenJDK 17 image as the base image for the build stage
FROM openjdk:17-jdk-slim as build

WORKDIR /app


COPY pom.xml .



RUN mvn dependency:go-offline


COPY src ./src


RUN chmod -R 755 /app/src


RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-slim


WORKDIR /app


COPY --from=build /app/target/dopamineDB-0.0.1-SNAPSHOT.jar app.jar




EXPOSE 8080


ENTRYPOINT ["java", "-jar", "/app/app.jar"]
