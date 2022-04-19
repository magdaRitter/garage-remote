package at.home.garageremote.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishNotificationEvent(final String message){
        NotificationEvent notificationEvent = new NotificationEvent(this, message);
        applicationEventPublisher.publishEvent(notificationEvent);
    }
}
