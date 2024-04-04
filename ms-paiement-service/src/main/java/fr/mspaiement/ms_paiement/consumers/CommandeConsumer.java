package fr.mspaiement.ms_paiement.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mspaiement.ms_paiement.constants.PaiementStatus;
import fr.mspaiement.ms_paiement.model.dto.request.PaiementRequest;
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
    private final KafkaTemplate<String, Paiement> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "commande-events")
    public void consume(String message) {
        try {
            log.info("Message reçu depuis Kafka : {}", message);
            // Convertir le message en objet PaiementRequest
            PaiementRequest paiementRequest = objectMapper.readValue(message, PaiementRequest.class);
            log.debug("Objet PaiementRequest créé à partir du message : {}", paiementRequest);
            // Créer l'entité Paiement
            Paiement paiement = Paiement.builder().commandeId(paiementRequest.getCommandeId()).customerId(paiementRequest.getCustomerId()).amount(paiementRequest.getAmount()).paiementStatus(PaiementStatus.SUCCESS) // Statut par défaut pour un nouveau paiement
                    .build();
            log.debug("Entité Paiement créée : {}", paiement);
            // Enregistrer l'entité Paiement dans la base de données
            Paiement savedPaiement = paiementRepository.save(paiement);
            log.info("Paiement enregistré dans la base de données : {}", savedPaiement);
            // Envoyer un événement de paiement à Kafka
            sendPaiementEvent(savedPaiement);
        } catch (Exception e) {
            log.error("Erreur lors du traitement du message Kafka : {}", message, e);
        }
    }
    // Méthode pour envoyer un événement de paiement à Kafka
    private void sendPaiementEvent(Paiement paiement) {
        try {
            log.debug("Envoi de l'événement de paiement à Kafka");
            kafkaTemplate.send("paiement-events", paiement);
            log.debug("Événement de paiement envoyé à Kafka");
        } catch (Exception e) {
            log.error("Erreur lors de l'envoi de l'événement de paiement à Kafka", e);
        }
    }
}


