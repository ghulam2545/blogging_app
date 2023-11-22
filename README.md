## Blogging-App REST API

Welcome to the Blogging-App REST API! This API is designed to support a simple blogging application with user management, post creation, commenting, and category organization.

### Technologies Used

- **Spring Boot**: The backend framework for building robust Java applications.
- **MySQL**: A relational database management system for data storage.

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- MySQL Database

### Setup

1. Clone the repository:

```
git clone https://github.com/ghulam2545/blogging_app.git
cd blogging-app
```

2. Configure the Database:

- Create a MySQL database for the application.
- Update the database configuration in application.properties.

3. Build and Run the Application:

```
./mvnw clean install
./mvnw spring-boot:run
```

4. The application will be accessible at http://localhost:8090
5. Access swagger UI from **http://localhost:8090/swagger-ui/index.html**

### Endpoints

- **User Controller:**
  Manage user information.

- **Post Controller:**
  Create, retrieve, and delete blog posts.

- **Comment Controller:**
  Add and remove comments on blog posts.

- **Category Controller:**
  Organize posts into categories.

### Project Structure

- **src/main/java/com.ghulam:** Java source code.
- **src/main/resources:** Application configuration files.

### Database Schema

![output screenshot](https://github.com/ghulam2545/blogging_app/blob/master/img/er.PNG)

### Contributing

Feel free to submit issues and pull requests.
