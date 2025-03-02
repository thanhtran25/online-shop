# Java Spring Boot Application

## Prerequisites
- Java 15 or later (Eclipse Temurin recommended)
- Docker (optional, if using containers)
- Docker Compose (if using `docker-compose.yml`)
- Maven Wrapper (`mvnw` included in the project)

## Setup

1. **Clone the repository:**
   ```sh
   git clone <repository-url>
   cd <project-directory>
   ```

2. **Copy the environment file:**
   ```sh
   cp .env.example .env
   ```
   Modify `.env` file with your specific configuration.

3. **Run the application:**

    - Using Maven Wrapper:
      ```sh
      ./mvnw spring-boot:run
      ```
    - On Windows:
      ```sh
      mvnw.cmd spring-boot:run
      ```

4. **Building the application:**
   ```sh
   ./mvnw clean package
   ```
   The built JAR file will be located in `target/`.

5. **Run the application using JAR:**
   ```sh
   java -jar target/<your-app-name>.jar
   ```

6. **Run the application using Docker Compose:**

    - Ensure Docker and Docker Compose are installed.
    - Build and start the application with:
      ```sh
      docker-compose up --build
      ```
    - To stop the containers:
      ```sh
      docker-compose down
      ```
    - If needed, restart the services without rebuilding:
      ```sh
      docker-compose up -d
      ```

## Additional Notes
- Ensure that all required environment variables are correctly set in `.env`.
- If using Docker, you can build and run the container manually with:
  ```sh
  docker build -t my-spring-app .
  docker run --env-file .env -p 8080:8080 my-spring-app
  ```

## License
This project is licensed under the MIT License.

