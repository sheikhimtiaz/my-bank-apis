# Software Engineer Test Assignment 
 MyBank - Account, reporting

Things to be checked before starting the application:

* Update the application.properties file with your postgres database name, username and password
* Create three tables with the commands from schema.sql
* Make sure that you have a rabbitmq server running and configured in application.properties (default is localhost:5671)

-------------------
### Tech stack:
- JDK 11
- Spring Boot
- Spring REST 
- MyBatis
- Lombok
- PostgreSQL
- RabbitMQ
- Swagger 2
-------------------
### How to compile and install:
it is a maven project , so just clean and install and should be ready ! 
```sh
mvn clean install 
```
-------------------
### How to run the locally :

Once you run the spint boot application locally from the main class, you can test the rest API on the browser via SWAGGER
