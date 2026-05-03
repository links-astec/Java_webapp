# Task Manager Web App

A Spring Boot RESTful API for managing personal tasks with user authentication, built using Java 17, Spring Boot, Spring Security, Spring Data JPA, JWT, and PostgreSQL. Includes OpenAPI/Swagger UI documentation.

## Features
- User registration and authentication (JWT-based)
- Secure endpoints with Spring Security
- CRUD operations for tasks (create, read, update, delete)
- Task attributes: title, description, status, priority, due date
- Each user manages their own tasks
- PostgreSQL for production, H2 for testing
- API documentation with Swagger UI

## Project Structure
```
src/
  main/
    java/com/example/webapp/
      WebAppApplication.java         # Main Spring Boot application
      config/SecurityConfig.java     # Security configuration (JWT, endpoints)
      controller/TaskController.java # REST endpoints for tasks
      dto/TaskDTO.java              # Data Transfer Objects for tasks
      model/Task.java, User.java    # JPA entities
      repository/                   # Spring Data JPA repositories
      service/TaskService.java      # Business logic for tasks
    resources/application.properties# App configuration
  test/java/...                     # Unit and integration tests
pom.xml                             # Maven build file
```

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- PostgreSQL (or use H2 for tests)

### Setup
1. Clone the repository:
   ```sh
   git clone <repo-url>
   cd webapp
   ```
2. Configure your database in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanager
   spring.datasource.username=postgres
   spring.datasource.password=your_password
   ```
3. Build and run the application:
   ```sh
   mvn spring-boot:run
   ```

### API Documentation
- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- OpenAPI docs: [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

## Main Endpoints
- `POST /api/auth/register` — Register a new user
- `POST /api/auth/login` — Authenticate and receive JWT
- `GET /api/tasks` — List all tasks for logged-in user
- `POST /api/tasks` — Create a new task
- `PUT /api/tasks/{id}` — Update a task
- `DELETE /api/tasks/{id}` — Delete a task

## Technologies Used
- Java 17
- Spring Boot 3
- Spring Security (JWT)
- Spring Data JPA
- PostgreSQL, H2 (test)
- Lombok
- Swagger/OpenAPI
- Maven

## License
MIT
