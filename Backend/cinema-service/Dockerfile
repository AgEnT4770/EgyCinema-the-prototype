# Step 1: Use a Java 25 runtime environment
FROM eclipse-temurin:25-jdk-alpine

# Step 2: Set the working directory
WORKDIR /app

# Step 3: Copy your jar (make sure you ran 'mvn package' first!)
COPY target/*.jar app.jar

# Step 4: Open the port your app uses
EXPOSE 8080

# Step 5: Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]