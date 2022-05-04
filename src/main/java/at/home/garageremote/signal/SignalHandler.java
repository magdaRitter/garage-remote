package at.home.garageremote.signal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class SignalHandler {

    private final SignalRequester signalRequester;

    @Autowired
    public SignalHandler(SignalRequester signalRequester) {
        this.signalRequester = signalRequester;
    }

    boolean requestSignal(SignalType signalType) {
        UUID id = generateId();

        log.debug("Sending signal request with id =" + id);

        return signalRequester.request(signalType, id);
    }

    private UUID generateId() {
        return UUID.randomUUID();
    }
}
