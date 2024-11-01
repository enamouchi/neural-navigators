# Stage 1: Build the application using Maven
FROM ubuntu:22.04 AS builder

# Install OpenJDK and Maven
RUN apt-get update && apt-get install -y openjdk-17-jdk maven wget && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Set the working directory in the container (optional but helpful)
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml .
COPY src ./src

# Run Maven to build the package, skipping tests for faster builds
RUN mvn clean package -Dmaven.test.skip=true

# Stage 2: Run the application using OpenJDK
FROM openjdk:17

# Set the working directory for the runtime stage (optional)
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=builder /app/target/tp-foyer-5.0.0.jar tp-foyer.jar

# Expose the port on which the application will listen
EXPOSE 8089

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "tp-foyer.jar"]
