FROM openjdk:11 as rabbitmq

RUN mkdir /opt/garage-remote
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} garage-remote.jar
ENTRYPOINT ["java","-jar","/garage-remote.jar"]
