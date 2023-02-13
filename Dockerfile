FROM openjdk:17-jdk-slim
MAINTAINER prussaq.net
COPY target/kameleoon-0.0.1-SNAPSHOT.jar kameleoon-api.jar
ENTRYPOINT ["java","-jar","/kameleoon-api.jar"]