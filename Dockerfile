FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/Supermarket-1.0-jar-with-dependencies.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]