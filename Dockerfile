FROM openjdk:8-jre-alpine
LABEL maintainer="sama.pozitiff@gmail.com"
WORKDIR /newspaper
COPY target/newspaper.jar /newspaper/newspaper.jar
ENTRYPOINT ["java","-jar","newspaper.jar"]