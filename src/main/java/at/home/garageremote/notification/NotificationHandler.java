package at.home.garageremote.notification;

import at.home.garageremote.event.NotificationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationHandler {

    @Autowired
    private NotificationEventPublisher notificationEventPublisher;
    @Autowired
    private NotificationParser notificationParser;

    public void publishNotification(String message) {
        String notification = notificationParser.parse(message);
        notificationEventPublisher.publishNotificationEvent(notification);
    }
}
