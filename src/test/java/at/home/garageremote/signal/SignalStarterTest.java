package at.home.garageremote.signal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;

@SpringBootTest
class SignalStarterTest {

    @Autowired
    private SignalHandler signalHandler;

//    @Autowired SignalStarter signalStarter;

    @Test
    public void shouldRequestGateSignal(){
        boolean expectedResult = true;
        boolean result = signalHandler.requestSignal(SignalType.GATE);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void shouldRequestGarageSignal(){
        boolean expectedResult = true;
        boolean result = signalHandler.requestSignal(SignalType.GARAGE);

        Assertions.assertEquals(expectedResult, result);
    }

//    @Test
//    public void shouldRapeJavaClass() throws IllegalAccessException {
//        Class<SignalStarter> signalStarterClazz = SignalStarter.class;
//        Field[] declaredFields = signalStarterClazz.getDeclaredFields();
//        for (Field declaredField : declaredFields) {
//            System.out.println(declaredField.getName());
//            declaredField.setAccessible(true);
//            if(declaredField.getName().equals("log")){
//                System.out.println("we have a logger here");
//                Logger logger = (Logger) declaredField.get(signalStarter);
//                logger.debug("raped.");
//            }
//        }
//    }
}