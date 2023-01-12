![GitHub Workflow Status](https://img.shields.io/github/workflow/status/claytoncastro/scheduler-microservice/maven.yml?label=Build)
![GitHub last commit](https://img.shields.io/github/last-commit/claytoncastro/scheduler-microservice?label=Last%20Commit)

# System for scheduling medical consultation

### Environment
* Java 1.8
* Maven 3.8.1
* Docker

### About

This is a system for scheduling medical consultation. Must save a schedule, but does not allow you to save Schedule 
with the same schedule date. In the future, a microservice will be implemented to send emails for the person who was 
scheduled as well as it's saves a history of schedules.

### First steps
You need to follow some steps to run this application.

* Install the docker on your machine.
* Run the file called **docker-compose.yml**. You can find this in the root folder of this project.
* Open the terminal in the root folder and run the following command:
    ~~~
    docker-compose up
    ~~~
This command will execute this file and mount a MySQL image in Docker.

You can see your databases into the docker using the command:
* Enter in Docker:
    ~~~
    docker exec -it mysql bash
    ~~~
* Enter in MySQL
    ~~~
    mysql -u root -p
    ~~~
    After "-u" you must enter the database user and press ENTER. Then, you must enter your password and press ENTER.
* See all yours databases using the command:
    ~~~
    show databases;
    ~~~
Before starting the application, make sure that the MySQL image status is "up" in Docker. Run the following command:
~~~
docker ps -a
~~~

#### Users
For use this application you must have one user to access the system. Run the sql command on the archive ***user-sql.sql***
on the path below;
~~~
  schedule-microservice/util/sql
~~~

**Now you are ready to start and test the application!**

### Documentation

You can see the documentation through of the URI below: 
~~~
  http://localhost:8081/swagger-ui.html
~~~

You have to be logged, you can use one of the users saved then.

* The user called "Admin Test" have the followed roles:
  ~~~
    ROLE_ADMIN, ROLE_USER
  ~~~
* The user called "User Test" have the followed roles:
  ~~~
    ROLE_USER
  ~~~

#### Roles Access

| Role Type  | GET      | POST      | PUT       | DELETE  |
:----------- | :------: | :-------: | :-------: | :-------:
| ROLE_ADMIN | X        | X         | X         | X       |
| ROLE_USER  | X        |           |           |         |

### More Details
This application is being built and have some things to be implemented yet.
