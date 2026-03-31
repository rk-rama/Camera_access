# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Sabse pehle pom.xml copy karke dependencies download karein (Caching ke liye)
COPY pom.xml .
RUN mvn dependency:go-offline

# Ab source code copy karein aur build karein
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Build stage se sirf jar file uthayein
COPY --from=build /app/target/*.jar app.jar

# Port 8080 expose karein (Render isse detect karega)
EXPOSE 8080

# Application run karein
ENTRYPOINT ["java", "-jar", "app.jar"]