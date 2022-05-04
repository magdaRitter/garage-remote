package at.home.garageremote.controller;

import at.home.garageremote.event.NotificationEvent;
import at.home.garageremote.signal.SignalStarter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/home")
public class HomeController implements ApplicationListener<NotificationEvent> {

    private final SignalStarter signalStarter;

    @Autowired
    public HomeController(SignalStarter signalStarter) {
        this.signalStarter = signalStarter;
    }

    @GetMapping
    public String getHomeForm() {
        return "home";
    }

    @PostMapping(params = "garage")
    public String garage() {
        String result = "error";
        boolean requestSentProperly = signalStarter.startGarageSignalRequest();

        if(requestSentProperly){
            result = "home";
        }

        return result;
    }

    @PostMapping(params = "gate")
    public String gate() {
        String result = "error";
        boolean requestSentProperly = signalStarter.startGateSignalRequest();

        if(requestSentProperly){
            result = "home";
        }

        return result;
    }

    @Override
    public void onApplicationEvent(NotificationEvent event) {
        log.info(event.getNotification());
    }
}
