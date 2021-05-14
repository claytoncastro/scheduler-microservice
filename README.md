# System for scheduling medical consultation

### Environment
* Java 1.8
* Maven 3.8.1
* Docker

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
Now you are ready to start the application.
