# Spring Boot and Spring Data Mongo



### Most Relevant Technologies Used:
* [Java 8](https://www.java.com/pt_BR/download/faq/java8.xml)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Data MongoDB](https://projects.spring.io/spring-data-mongodb/)
* [Lombok](https://projectlombok.org/)
* [Jackson](https://github.com/FasterXML/jackson)
* [Junit](https://junit.org/junit4/)
* [Mockito](https://site.mockito.org/)
* [Hamcrest](http://hamcrest.org/)
* [Gradle](https://gradle.org/)
* [MongoDB](https://www.mongodb.com/)
* [Docker](https://www.docker.com/)

### Requirements:

* [JDK 8](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) installed and configured in path.
* [Docker](https://docs.docker.com/install/) installed and configured in path (deploy).
* [Lombok IDEs](https://projectlombok.org/setup/overview) to import the project into IDE.

### Running the Project:

#### Running Locally:
```
gradlew bootRun
```

#### Running the Tests:
```
gradle test
```


#### Deploy (Docker):
```
gradlew distDocker
docker run -p 8080:8080 spring-boot-data-mongo:0.0.1
```


