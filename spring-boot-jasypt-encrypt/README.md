Spring Boot [Jasypt](https://github.com/ulisesbocchio/jasypt-spring-boot) Encrypt
---------------------------------

In this project we can see how use the [Jasypt](http://www.jasypt.org/) with Spring Boot for encrypt the properties in application.propeties.

#### To Encrypt the property:
```
encrypt input=spring-boot-jasypt-encrypt password=password
```
for more detail about how use jasypt command line see [here](http://www.jasypt.org/cli.html)

#### Run project:
```
gradlew bootRun
```

#### For see the property decrypted:
```
http://localhost:8080/property
```

