package at.home.garageremote.rabbitmq;

import at.home.garageremote.cipher.Encryptor;
import at.home.garageremote.notification.NotificationHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Getter
public class MessageReceiver {

    @Autowired
    private NotificationHandler notificationHandler;
    @Autowired
    private Encryptor encryptor;

    public void receiveMessage(byte[] bytes) {
        String message = new String(bytes);
        log.info("Received message from rabbit as bytes " + message);

        try {
            message = encryptor.decrypt(message);
            notificationHandler.publishNotification(message);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Could not decrypt received message!");
        }
    }

    public void receiveMessage(String message) {
        log.info("Received message from rabbit as string");
        notificationHandler.publishNotification(message);
    }

}