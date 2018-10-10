# Spring Boot and Spring Data Mongo



### Tecnologias Mais Relevantes Utilizadas:
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

### Requisitos:

* [JDK 8](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) instalado e configurado no path.
* [Docker](https://docs.docker.com/install/) instalado e configurado no path (deploy).
* [Lombok IDEs](https://projectlombok.org/setup/overview) para importação do projeto em IDE.

### Exceutando o Projeto:

> O [Gradle Wrrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) foi utilizado no projeto, então não é obrigatório sua instalação.Para executar em Windows use **gradlew** em Linux **./gradlew**

#### Excecutando Localmente:
```
gradlew bootRun
```
> O primeiro startup pode levar alguns minutos já que o Gradle resolverá todas as dependências do projeto e o Spring Boot realizará o download do MongoDB no seu startup.

#### Executando os Testes:
```
gradle test
```


#### Deploy (Docker):
```
gradlew distDocker
docker run -p 8080:8080 spring-boot-data-mongo:0.0.1
```


