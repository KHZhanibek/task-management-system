
# Task Management System (WEB API)

Web API for entering project data into the database

# Technological stack

    1. Maven
    2. Spring Core
    3. Spring Data (Hibernate & PostgreSQL)
    4. Thymeleaf
    5. Swagger

# Database configuration

Create a PostgreSQL database with the name `task_management` and add 
the credentials to `/resources/application.properties.`

The default ones are:/
spring.datasource.url=jdbc:postgresql://localhost:5432/task_management
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.username=postgres
spring.datasource.password=1245emer

# Usage

Run the project through the IDE and head out to http://localhost:8080 \

Web documentation is available at http://localhost:8080/swagger
