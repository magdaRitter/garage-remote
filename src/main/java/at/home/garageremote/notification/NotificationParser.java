package at.home.garageremote.notification;

import at.home.garageremote.signal.SignalType;
import org.springframework.stereotype.Component;

@Component
public class NotificationParser {

    public String parse(final String message) {
        String retValue = message;

        if(message.contains(SignalType.GARAGE.toString())){
            retValue = "A garage signal was sent out";
        }
        if(message.contains(SignalType.GATE.toString())){
            retValue = "A gate signal was sent out";
        }

        return retValue;
    }
}
