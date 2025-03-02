FROM eclipse-temurin:23-jdk AS build

WORKDIR /app

# Copy Maven Wrapper & dependencies first to cache them
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copy the source code and build the project
COPY src/ src/
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:23-jre AS production

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
