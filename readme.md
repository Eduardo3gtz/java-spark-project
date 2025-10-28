# Collectible Store API (Spark Java)

## Overview

The **Collectible Store API** is a REST API designed for an online store specializing in collectible items. This project is built using **Java** and the **Spark micro-framework**, providing a lightweight and efficient solution for handling HTTP requests and responses.

**Current Status:** Sprint 1 has been completed, establishing the project foundation and implementing basic CRUD operations for user management with simulated data.

---

## Technologies Used

This project leverages the following technologies:

- **Java 17** - Core programming language
- **Maven** - Build automation and dependency management
- **Spark Framework** (`spark-core`) - Lightweight micro-framework for building web applications
- **Gson** - JSON serialization and deserialization library
- **Logback** - Logging framework (SLF4J implementation)
  - *Note: SLF4J warnings during startup are known and non-critical at this stage*

---

## Features Completed (Sprint 1)

Sprint 1 focused on establishing the project infrastructure and implementing basic user API functionality:

- ✅ Maven project setup with `pom.xml` configuration
- ✅ Basic project structure created (`src/main/java`, etc.)
- ✅ `Main.java` application entry point implemented
- ✅ Spark Framework initialized and configured
- ✅ API routes defined for `/users` resource:
  - `GET /users` - Retrieves a sample list of all users
  - `GET /users/:id` - Retrieves a specific user by ID or returns 404 if not found
  - `POST /users` - Simulates adding a new user (returns 201 Created)
  - `PUT /users/:id` - Simulates updating an existing user (returns 200 OK)
  - `DELETE /users/:id` - Simulates deleting a user (returns 204 No Content)
  - `OPTIONS /users/:id` - Checks user existence and available methods (returns 200 or 404)
- ✅ API responses configured to return `application/json` content type
- ✅ Basic request handling logic implemented with simulated data

---

## Getting Started

### Prerequisites

Before running the application, ensure you have the following installed:

- **JDK 17** or higher
- **Maven 3.6+** (configured in your system PATH)

### Clone the Repository
```bash
git clone <your-repo-url>
cd collectible-store-api
```

### Compile & Package

Build the project and create an executable JAR with all dependencies:
```bash
mvn clean compile assembly:single
```

This command will generate a JAR file in the `target/` directory named `collectible-store-api-1.0-SNAPSHOT-jar-with-dependencies.jar`.

### Run the Application

Execute the generated JAR file:
```bash
java -jar target/collectible-store-api-1.0-SNAPSHOT-jar-with-dependencies.jar
```

The application will start and listen on **`http://localhost:4567`** by default.

---

## API Endpoints (Sprint 1)

The following endpoints are currently available for the `/users` resource:

| Method   | Path           | Description                                      | Response Status |
|----------|----------------|--------------------------------------------------|-----------------|
| `GET`    | `/users`       | Retrieve all users (sample data)                 | 200 OK          |
| `GET`    | `/users/:id`   | Retrieve a specific user by ID                   | 200 OK / 404    |
| `POST`   | `/users`       | Add a new user (simulated)                       | 201 Created     |
| `PUT`    | `/users/:id`   | Update an existing user (simulated)              | 200 OK          |
| `DELETE` | `/users/:id`   | Delete a user (simulated)                        | 204 No Content  |
| `OPTIONS`| `/users/:id`   | Check user existence and available methods       | 200 OK / 404    |

**Note:** All endpoints currently return simulated/sample data. Database integration will be implemented in future sprints.

### Example Usage
```bash
# Get all users
curl http://localhost:4567/users

# Get user by ID
curl http://localhost:4567/users/1

# Add a new user
curl -X POST http://localhost:4567/users \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com"}'

# Update a user
curl -X PUT http://localhost:4567/users/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Jane Doe","email":"jane@example.com"}'

# Delete a user
curl -X DELETE http://localhost:4567/users/1

# Check user existence
curl -X OPTIONS http://localhost:4567/users/1
```

---

## Next Steps

### Sprint 2 (Upcoming)
- Implement templates/views for rendering HTML responses
- Add comprehensive exception handling and error responses
- Improve logging and request/response validation

### Sprint 3 (Planned)
- Implement item filtering and search functionality
- Add WebSocket support for real-time features
- Integrate persistent data storage (database)

---



**Project Status:** 🚧 In Development - Sprint 1 Complete