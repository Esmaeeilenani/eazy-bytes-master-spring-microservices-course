FROM openjdk:21-jdk-slim AS build

#the devs/company maintainig this docker image
MAINTAINER esmaeeeil

# install curl (and CA certs for HTTPS), then clean apt cache
RUN apt-get update \
 && apt-get install -y --no-install-recommends curl ca-certificates \
 && rm -rf /var/lib/apt/lists/*


#copy the jar inside the target folder to image
COPY target/*.jar app.jar

#command resposible for running the jar using java
ENTRYPOINT ["java","-jar","app.jar"]