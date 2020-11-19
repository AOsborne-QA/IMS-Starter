Coverage: 80.3%
# Inventory Management System (IMS)

IMS-Starter is an inventory management interface that has been built with Create, Read, Update, Delete (CRUD) functionality in mind that allows a user these options when working with customers, items and orders and interacting with a MySQL database.

This has been built using Java as the main technology, compiled using Maven and tested using JUnit & Mockito.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

In order for you to be able to further develop and utilise this project, you require the following:

- [Java 1.8.0](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- [Apachace Maven V3.63](https://maven.apache.org/download.cgi)
- [MySQL v8.022](https://www.mysql.com/downloads/) or later - Local or hosted
- IDE of your choice such as [Eclipse](https://www.eclipse.org/downloads/)
- [Git Bash](https://git-scm.com/downloads)

### Installing

To run this project locally in a development environment, you can follow the below steps:

#### Obtaining The Repository

1. Fork this repository into your own.
2. Using **Git Bash**, clone your respository onto your local machine using ``git clone``.
3. Create your required branches for development.
4. Open your IDE such as **Eclipse**.
5. Open the project as a **Maven project**
6. Run project as a Java application to use it.

#### Setting up MySQL

1. Open your **MySQL** workbench.
2. Create a database called ``ims``.

Alternatively, to run the application itself without using an IDE you can view the [deployment section](#deployment).

## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

Explain what these tests test, why and how to run them

```
Give an example
```

### Integration Tests 
Explain what these tests test, why and how to run them

```
Give an example
```

## Deployment

To delpoy a live version of this application you will need to do the following:

Note: Please ensure that you have the prerequisites and your ``ims`` database setup on MySQL localhost before proceeding with the below.

1. Fork this repository into your own.
2. Using **Git Bash**, clone your respository onto your local machine using ``git clone``.
3. Open a terminal such as ``CMD`` or ``Windows Powershell``
4. Change directory to where you placed the repository on your local machine.
5. Input the following to launch the application using the ``ims-0.0.1-jar-with-dependencies.jar`` within the main ``IMS-Starter`` respository:
  - ```java -jar FILELOCATION*```
  * i.e. ``C\USERS\USER\DESKTOP\IMS-Starter\ims-0.0.1-jar-with-dependencies.jar``
6. Input your credentials to access your localhost test mySQL database.
7. You are now able to use the application:

![Customer creation example](https://res.cloudinary.com/andy-osborne/image/upload/v1605790811/IMS/Hnet.com-image_ghoamw.gif)

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Andy Osborne** - *Finalisation project* - [AOsborne-QA](https://github.com/AOsborne-QA/)
## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Thank you to Piers Barber & Aswene Sivaraj for their patience in teaching me Java and assisting with queries.
