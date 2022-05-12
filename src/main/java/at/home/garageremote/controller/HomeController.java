package at.home.garageremote.controller;

import at.home.garageremote.event.NotificationEvent;
import at.home.garageremote.signal.SignalRequestType;
import at.home.garageremote.signal.SignalResponse;
import at.home.garageremote.signal.SignalStarter;
import at.home.garageremote.signal.SignalType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController implements ApplicationListener<NotificationEvent> {

    private final SignalStarter signalStarter;

    @Autowired
    public HomeController(SignalStarter signalStarter) {
        this.signalStarter = signalStarter;
    }

    @PostMapping(value = "/signal")
    SignalResponse garage(@RequestBody SignalRequestType signalRequestType){
        if(signalRequestType.getSignalType() == SignalType.GARAGE){
            return new SignalResponse(signalStarter.startGarageSignalRequest());
        }
        if(signalRequestType.getSignalType() == SignalType.GATE){
            return new SignalResponse(signalStarter.startGateSignalRequest());
        }
        if(signalRequestType.getSignalType() == SignalType.BOTH){
            return new SignalResponse(signalStarter.startBothSignalRequests());
        }

        return new SignalResponse(false);
    }

    @Override
    public void onApplicationEvent(NotificationEvent event) {
        log.info(event.getNotification());
    }
}
