package at.home.garageremote.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(byte[] encryptedMessageBytes) {
        rabbitTemplate.convertAndSend(Configurator.topicExchangeName, Configurator.routingKey, encryptedMessageBytes);

        log.debug("Message was sent out");
    }
}
