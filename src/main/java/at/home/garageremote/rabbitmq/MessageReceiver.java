package at.home.garageremote.rabbitmq;

import at.home.garageremote.notification.NotificationHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
@Getter
public class MessageReceiver {

    @Autowired
    private NotificationHandler notificationHandler;
    private final CountDownLatch latch = new CountDownLatch(1);


    public void receiveMessage(String message) {
        latch.countDown();
        notificationHandler.publishNotification(message);
    }
}