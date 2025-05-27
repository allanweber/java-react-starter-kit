# Backend Project

This is the backend project for the starter kit, built with Spring Boot, JPA, and PostgreSQL.

## Prerequisites

- Java 21
- Maven
- PostgreSQL running on localhost:5432 with a database named 'mydatabase' and credentials (username: postgres, password: postgres)

## Running the Project

1. Navigate to the backend directory:
   ```
   cd backend
   ```

2. Build the project:
   ```
   mvn clean install
   ```

3. Run the application:
   ```
   mvn spring-boot:run
   ```

4. Access the OpenAPI documentation:
   - Swagger UI: http://localhost:8080/swagger-ui/index.html
   - OpenAPI JSON: http://localhost:8080/api-docs

## Project Structure

- `src/main/java/com/example/backend/BackendApplication.java`: Main Spring Boot application class.
- `src/main/java/com/example/backend/controller/HelloController.java`: Sample REST controller with OpenAPI annotations.
- `src/main/resources/application.properties`: Configuration file for the application. 