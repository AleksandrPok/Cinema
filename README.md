# Philharmonic Application

Implementation of an online store, where users can buy tickets to the concert.

## Structure

The project has an N-tier structure:
 - Database layer;
 - DAO layer;
 - Service layer;
 - Controllers layer.

#### User functions:
 - registration;
 - log in;
 - add tickets to a shopping cart;
 - delete tickets from shopping cart;
 - complete orders;
 - look active events.

#### Admin functions:
 - all user actions;
 - add new Stages, Concerts and Concert Sessions;
 - update exist sessions;
 - delete exist sessions;
 - view information about registered users.

##Technologies
 - Java 11
 - Hibernate
 - Spring MVC
 - Spring Security
 - Javax Servlet API
 - Maven
 - MySQL
 - Apache Tomcat

##Running the Application
1. Download and install JDK;
2. Download and install servlet container;
3. Download and install MySQL Server;
4. Setup connection properties in db.properties file:
 - user: "your username"
 - password: "your password"
 - db.url=jdbc:mysql://localhost/your_db_name?serverTimezone=EET


