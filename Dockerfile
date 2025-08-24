# Multi-stage build for Spring Boot application
FROM maven:3.9.5-openjdk-17 AS build

# Set working directory
WORKDIR /app

# Copy parent POM
COPY pom.xml ./

# Copy all module POMs
COPY advance-tech-*/pom.xml ./

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-jre-slim

# Set working directory
WORKDIR /app

# Create non-root user
RUN groupadd -r advancetech && useradd -r -g advancetech advancetech

# Copy the built JAR from build stage
COPY --from=build /app/advance-tech-web/target/*.jar app.jar

# Create uploads directory
RUN mkdir -p /app/uploads && chown -R advancetech:advancetech /app

# Switch to non-root user
USER advancetech

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
