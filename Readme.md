# Pius' Spring Boot

## Make jar file
```bash
gradlew.bat bootJar
```

## Run jar file
```bash
java -jar build/libs/spring-boot-0.0.1-SNAPSHOT.jar # default
java -jar build/libs/spring-boot-0.0.1-SNAPSHOT.jar --debug # debug
java -jar build/libs/spring-boot-0.0.1-SNAPSHOT.jar --greeting.name=pius # set greeting.name
```