FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/garage-remote
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} garage-remote.jar
ENTRYPOINT ["java","-jar","/garage-remote.jar"]
