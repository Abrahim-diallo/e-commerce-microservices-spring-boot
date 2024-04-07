package fr.mspaiement.ms_paiement.consumers;

import fr.mspaiement.ms_paiement.constants.PaiementStatus;
import fr.mspaiement.ms_paiement.eventkafka.CommandePlacedEvent;
import fr.mspaiement.ms_paiement.eventkafka.PaiementEvent;
import fr.mspaiement.ms_paiement.model.entities.Paiement;
import fr.mspaiement.ms_paiement.repository.PaiementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CommandeConsumer {

    private final PaiementRepository paiementRepository;
    private final KafkaTemplate<String, PaiementEvent> kafkaTemplate;

    // Méthode de consommation d'événement
    @KafkaListener(topics = "commande-events")
    public void consume(CommandePlacedEvent commandePlacedEvent) {
        // Log de l'événement consommé
        log.info("CommandePlacedEvent consommé: {}", commandePlacedEvent);
        // Création de l'entité Paiement à partir de l'événement
        log.debug("Création de l'entité Paiement: {}", commandePlacedEvent);
        Paiement paiementEntity = Paiement.builder()
                .commandeId(commandePlacedEvent.getCommandeId())
                .customerId(commandePlacedEvent.getCustomerId())
                .amount(commandePlacedEvent.getTotalAmount().doubleValue())
                .paiementStatus(PaiementStatus.SUCCESS)
                .build();
        // Log de l'entité Paiement créée
        log.info("Entité Paiement créée: {}", paiementEntity);
        // Sauvegarde de l'entité Paiement dans la base de données
        log.debug("Sauvegarde de l'entité Paiement dans la base de données");
        var payment = paiementRepository.save(paiementEntity);
        // Log de l'entité Paiement sauvegardée
        log.debug("Entité Paiement sauvegardée dans la base de données: {}", payment);
        // Envoi de l'événement de paiement
        sendPaymentEvent(PaiementEvent.builder()
                .paiementId(payment.getId())
                .commandeId(payment.getCommandeId())
                .customerId(payment.getCustomerId())
                .amount(payment.getAmount())
                .build());
    }

    // Méthode pour envoyer un événement de paiement
    public void sendPaymentEvent(PaiementEvent paiementEvent) {
        // Log de l'envoi de l'événement de paiement
        log.debug("Envoi de l'événement de paiement à Kafka");
        kafkaTemplate.send("paiement-events", paiementEvent);
        // Log de l'événement de paiement envoyé avec succès
        log.debug("Événement de paiement envoyé à Kafka avec succès");
    }
}
