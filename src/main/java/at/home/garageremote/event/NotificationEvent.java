package at.home.garageremote.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class NotificationEvent extends ApplicationEvent {

    private final String notification;

    public NotificationEvent(Object source, String notification) {
        super(source);
        this.notification = notification;
    }
}
