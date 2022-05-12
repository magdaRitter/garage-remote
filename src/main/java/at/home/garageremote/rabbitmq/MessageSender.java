package at.home.garageremote.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
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
        try {
            rabbitTemplate.convertAndSend(Configurator.topicExchangeName, Configurator.routingKey, encryptedMessageBytes);
        } catch (AmqpException e) {
            e.printStackTrace();
            log.error("AMQp exception was thrown while trying to send message");
        }

        log.debug("Message was sent out");
    }
}
