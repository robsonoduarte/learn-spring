[Spring Boot](https://spring.io/projects/spring-boot) And [JMS](https://en.wikipedia.org/wiki/Java_Message_Service) with [ActiveMQ](http://activemq.apache.org/)
---------------------------------

Demo project for Spring Boot 2 Using the ActiveMQ Embedded with Topic And Queue

In this project we can see how use the Spring Boot with one Topic and one Queue in same project.


1 - [JMS Configuration](https://github.com/robsonoduarte/learn-spring/blob/master/spring-boot-jms-activemq/src/main/java/br/com/mystudies/springboot/jms/activemq/config/JMSConfig.java)

2 - [Sender and Consumer of Topic](https://github.com/robsonoduarte/learn-spring/tree/master/spring-boot-jms-activemq/src/main/java/br/com/mystudies/springboot/jms/activemq/topic)

3 - [Sender and Consumer of Queue](https://github.com/robsonoduarte/learn-spring/tree/master/spring-boot-jms-activemq/src/main/java/br/com/mystudies/springboot/jms/activemq/queue)

##### Run Project:
```
mvnw spring-boot:run
```

##### Send Message to Topic:
```
http://localhost:8080/topic
```

##### Send Message to Queue:
```
http://localhost:8080/queue
```




