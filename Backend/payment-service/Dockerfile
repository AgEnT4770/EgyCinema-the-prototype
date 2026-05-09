# Stage 1: Build (Keep this as is)
FROM maven:3.9.9-eclipse-temurin-21 AS build 
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run (Update this part)
FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app
# This line is the fix: it uses a wildcard (*) to find the JAR 
# regardless of the version number
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]