package fr.msnotifications.ms_notifications.consumer;

import fr.msnotifications.ms_notifications.events.PaiementEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PaiementConsumer {
    @KafkaListener(topics="paiement-events")
    public void consumerPaisement(PaiementEvent paiementEvent) {
        log.info("Paisement event: " + paiementEvent);
        log.info("Sending notification: " + paiementEvent.getPaiementId() + " to " + paiementEvent.getCommandeId() + " " + paiementEvent.getCustomerId() + " " + paiementEvent.getCustomerId());
        log.info("super cool notification message sent!!!!!!!!");
    }
}
