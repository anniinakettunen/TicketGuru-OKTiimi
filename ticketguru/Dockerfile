# Use official Java runtime image
FROM eclipse-temurin:17-jre

# Set working directory inside container
WORKDIR /app

# Copy the built JAR into container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose port that Spring Boot will run on
EXPOSE 8080

# Command to run the app with test profile (PostgreSQL)
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=test"]
