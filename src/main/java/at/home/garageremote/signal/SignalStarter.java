package at.home.garageremote.signal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SignalStarter {

    private SignalHandler signalHandler;

    @Autowired
    public SignalStarter(SignalHandler signalHandler) {
        this.signalHandler = signalHandler;
    }

    public boolean startGateSignalRequest() {
        log.debug("Request for gate signal will be fired");

        return signalHandler.requestSignal(SignalType.GATE);
    }

    public boolean startGarageSignalRequest() {
        log.debug("Request for garage signal will be fired");

        return signalHandler.requestSignal(SignalType.GARAGE);
    }
}
