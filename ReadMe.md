# ChatApplication_Project
### Requirements
* IntelliJIDEA
* ServerPort: 8080 (use: localhost:8080)
* Java version: 17
* Everything is present in the pom.xml (no need to download any library)
### Steps to run the Project
* Download the source code and import in intellijIDEA.
* Go to localhost:8080/
* Put specific end_points besides the port accordingly to run the project to access and modify the data
# Dependencies
## Validation
* Bean Validation with Hibernate validator.
## H2 Database
* Provides a fast in-memory database that supports JDBC API and R2DBC access, with a small (2mb) footprint. Supports embedded and server modes as well as a browser based console application.
## Spring Web
* Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
## Spring Boot DevTools
* Provides fast application restarts, LiveReload, and configurations for enhanced development experience.
## Spring Data JPA
* Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
## Lombok
* Java annotation library which helps to reduce boilerplate code.
## JSON
* JSON is a lightweight, language-independent, data interchange format. See http://www.JSON.org/ The files in this package implement JSON encoders/decoders in Java. It also includes the capability to convert between JSON and XML, HTTP headers, Cookies, and CDL. This is a reference implementation.
## MySQL
* MySQL JDBC driver.
# Project Structure(MVC Structure)
## Model
* UserModel
* StatusModel
* ChatHistory

## Controller
* UserController
* StatusController
* ChatHistoryController

## DAO
* UserRepo
* StatusRepo
* ChatHistoryRepo

## Service
* UserService
* StatusService
* ChatHistoryService

 ## Util
* CommonUtil --> which has the validation part for number, password and email etc
# EndPoints


# Working
* Project mainly focuses on Users, status of users and messages sent to other users
* Crud operations on User like add user,fetch user,update user,delete user
* Crud operation on Status adding of status had only two types active and de-active
* Crud operation on ChatHistory where adding of chats or messages and fetching of messages is performed in the project
* User sends message to other user only if both the user's status is active for that I used certain validations 
* We can get what are the messages sent by the user and to whom messages are sent and at what time messages were sent everything is known through the command or through end-points
* Various Native Queries were written in the project for the ease utilization and smooth handling without any errors
* All the can be seen in the mySql workbench