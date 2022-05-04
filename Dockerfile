FROM openjdk:11 as rabbitmq

WORKDIR /opt/garage-remote

ARG JAR_FILE=target/*.jar
ARG ENCRYPTION_KEY_FILE=target/classes/keys/encryption-public-key.pem
ARG DECRYPTION_KEY_FILE=target/classes/keys/decryption-private-key.pem

COPY ${ENCRYPTION_KEY_FILE} keys/encryption-public-key.pem
COPY ${DECRYPTION_KEY_FILE} keys/decryption-private-key.pem
COPY ${JAR_FILE} garage-remote.jar

ENTRYPOINT ["java","-jar","garage-remote.jar"]
