Spring Boot Active Profiles
---------------------------------

In this project we can see how use active profiles of Spring Boot to get properties by Environment:

[1 - Applications Properties](https://github.com/robsonoduarte/learn-spring/tree/master/spring-boot-active-profiles/src/main/resources)

[2 - Inject Properties](https://github.com/robsonoduarte/learn-spring/blob/master/spring-boot-active-profiles/src/main/java/br/com/mystudies/springboot/activeprofiles/controller/InjectPropertiesController.java)

[3 - Using Environment Interface](https://github.com/robsonoduarte/learn-spring/blob/master/spring-boot-active-profiles/src/main/java/br/com/mystudies/springboot/activeprofiles/controller/EnvController.java)

#### Run in Development Environment 
```
  mvnw spring-boot:run -Dspring.profiles.active=dev
```

#### Run in Production Environment 
```
  mvnw spring-boot:run -Dspring.profiles.active=prd
```

#### See Properties Values
```
  http://localhost:8080/injected/properties
  http://localhost:8080/env/properties
```
