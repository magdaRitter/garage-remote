package at.home.garageremote.signal;

import at.home.garageremote.rabbitmq.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class SignalRequester {

    private MessageSender messageSender;

    @Autowired
    public SignalRequester(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public boolean request(SignalType signalType, UUID id) {

        boolean result = false;

        // prepare message
        log.debug("Preparing message");
        String message = signalType.toString() + "_" + id.toString();

        // encrypt message
        log.debug("Encrypting message");

        // send message
        log.debug("Sending message");
        try {
            messageSender.sendMessage(message);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
