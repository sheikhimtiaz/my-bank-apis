# Spring pet project 
 MyBank - AccountService, ReportingService

Things to be checked before starting the application:

* Update the application.properties file with your postgres database name, username and password
* Create three tables with the commands from schema.sql
* Make sure that you have a rabbitmq server running and configured in application.properties (default is localhost:5672). RabbitMQ should have a direct exchange named “bank-user” with routing-key “account” to the queue “bank-user-account” and routing-key “transaction” to the queue “bank-user-transaction”.

-------------------
### Tech stack that I tried:
- JDK 11
- Spring Boot
- Spring REST 
- MyBatis
- Lombok
- PostgreSQL
- RabbitMQ
- Swagger 2
-------------------
### Getting Started
It is a gradle project , so open the project in your preferable ide and run from there. The project should run with JDK 11 and two service will run on separate ports by default, shouldn't be any problem. If you face any problem running the application, please contact me.

-------------------
### Testing

Once you run the spint boot application locally from the main class, you can test the rest API on the browser via SWAGGER http://localhost:8080/swagger-ui.html#/

```sh
http://localhost:8080/swagger-ui.html#/
```
-------------------
