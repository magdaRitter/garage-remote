package at.home.garageremote.signal;

import at.home.garageremote.cipher.Encryptor;
import at.home.garageremote.rabbitmq.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class SignalRequester {

    private final MessageSender messageSender;

    @Autowired
    private Encryptor encryptor;

    @Autowired
    public SignalRequester(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public boolean request(SignalType signalType, UUID id) {

        boolean result = false;

        // prepare message
        log.debug("Preparing message");
        String message = signalType.toString() + "_" + id.toString();

        // encrypt and send message
        log.debug("Encrypting and sending message");
        try {
            byte[] encryptedMessage = encryptor.encrypt(message);
            messageSender.sendMessage(encryptedMessage);
            result = true;
        } catch (Exception e) {
            log.debug("Could not send encrypted message!");
            e.printStackTrace();
        }

        return result;
    }
}
