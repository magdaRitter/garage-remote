package at.home.garageremote.rabbitmq;

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

    public void receiveMessage(byte[] bytes) {
        String message = new String(bytes);
        log.info("Received <" + message + ">");
        notificationHandler.publishNotification(message);
    }

    public void receiveMessage(String message) {
        log.info("Received <" + message + ">");
        notificationHandler.publishNotification(message);
    }

}