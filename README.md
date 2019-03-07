# Todo List REST API 
A Spring Boot REST API backend for todo list, utilizing Spring Data JPA to store and retrieve data from the database. The user may use the endpoints (specific documentation provided below) to create, read, update, and delete todo lists and entries. No UI is provided, however, the user may implement a front-end of their choice to consume the REST API.

## Swagger Rest API documentation here (after starting application)
http://localhost:8080/todo/swagger-ui.html

## Database Configuration
Use any relational database with the application. For MySQL users, a MySQL script is provided in [sql-scripts](https://github.com/thejaneshin/todo-list/tree/master/todo-list/sql-scripts) of this project. Make sure to uncomment and edit the url, username, and password in [application.properties](https://github.com/thejaneshin/todo-list/blob/master/todo-list/src/main/resources/application.properties). For example:
```
spring.datasource.url=jdbc:mysql://localhost:3306/todo-database
spring.datasource.username=user
spring.datasource.password=pass
```

## Running to-do list in command line
```
git clone https://github.com/thejaneshin/todo-list.git
cd todo-list/todo-list
mvnw spring-boot:run
```
You can then access todo-list here: http://localhost:8080/todo/

## Working with todo-list in Eclipse
The following items should be installed in your system:
* Maven (https://maven.apache.org/)
* git command line tool (https://help.github.com/articles/set-up-git)
* Eclipse with m2e plugin (http://eclipse.org/m2e/download/)

### Steps:
1. In the command line
```
git clone https://github.com/thejaneshin/todo-list.git
```
2. Inside Eclipse
```
File -> Import -> Maven -> Existing Maven projects
```
