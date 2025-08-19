FROM openjdk:21-jdk-slim AS build

#the devs/company maintainig this docker image
MAINTAINER esmaeeeil

#copy the jar inside the target folder to image
COPY target/*.jar app.jar

#command resposible for running the jar using java
ENTRYPOINT ["java","-jar","app.jar"]