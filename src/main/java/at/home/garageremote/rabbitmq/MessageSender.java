package at.home.garageremote.rabbitmq;

import at.home.garageremote.GarageRemoteApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;
    private final MessageReceiver messageReceiver;

    @Autowired
    public MessageSender(MessageReceiver messageReceiver, RabbitTemplate rabbitTemplate) {
        this.messageReceiver = messageReceiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(GarageRemoteApplication.topicExchangeName, GarageRemoteApplication.routingKey, message);

        log.debug("Message was sent out");
    }
}
