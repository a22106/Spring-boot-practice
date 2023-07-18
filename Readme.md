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

## Install Redis on Windows

```bash
# On powershell
wsl --install Ubuntu-22.04 # install Ubuntu 22.04
```

```bash
sudo apt-get update
sudo apt-get install redis-server # install redis-server

redis-server --version # check version
redis-cli # start redis-cli
# When redis-cli is running type ping to check if it is working
# If it is working it will return PONG 
```