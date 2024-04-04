package fr.pmuchallenger.ms_commande.services.Impl;
import fr.pmuchallenger.ms_commande.exceptions.CommandeNotFoundException;
import fr.pmuchallenger.ms_commande.kafkaEvent.CommndePlacedEvent;
import fr.pmuchallenger.ms_commande.mappers.CommandeMapper;
import fr.pmuchallenger.ms_commande.model.CommandeStatus;
import fr.pmuchallenger.ms_commande.model.dto.request.AddCommandeRequest;
import fr.pmuchallenger.ms_commande.model.dto.response.CommandeDTO;
import fr.pmuchallenger.ms_commande.model.entities.Commande;
import fr.pmuchallenger.ms_commande.repository.CommandeRepository;
import fr.pmuchallenger.ms_commande.services.CommandeService;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class CommandeServiceImpl implements CommandeService {
    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;
    private final MeterRegistry meterRegistry;
    private final KafkaTemplate<String, CommndePlacedEvent> kafkaTemplate;

    @Override
    public CommandeDTO addCommndae(AddCommandeRequest addCommandeRequest) {
        meterRegistry.counter("commande-service", "methode", "createCommande").increment();
        log.debug("create commande is called");
        Commande commande = commandeRepository.save(commandeMapper.toEntity(addCommandeRequest));
        kafkaTemplate.send("commande-events", new CommndePlacedEvent(
                commande.getId(),
                commande.getCustomerId(),
                commande.getTotalAmount(),
                commandeMapper
                        .toCommandeItemEventList(commande.getCommandeItems())));
        return commandeMapper.toDto(commande);

    }

    @Override
    public List<CommandeDTO> getAllCommandes() {
        meterRegistry.counter("commande", "mehtod", "getAllCommandes").increment();
        log.info("Gell all commandes is called");
        return commandeRepository.findAll()
                .stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommandeDTO getCommandeById(Long id) {
        //Verifiction l'existence de l'id commande: TODO
        meterRegistry.counter("commande-service", "mehtod", "getCommandeById").increment();
        return commandeRepository.findById(id)
                .map(commandeMapper::toDto)
                .orElseThrow();
            
    }

    @Override
    public CommandeDTO updateCommande(Long id, AddCommandeRequest addCommandeRequest) {
        // Incrémente le compteur de la méthode updateCommande
        meterRegistry.counter("commande-service", "methode", "updateCommande").increment();
        // loger  l'appel à la méthode avec l'ID de la commande
        log.debug("Update commande with id: {}", id);
        log.info("Update commande with id: {}", id);
        // Recherche la commande par son ID ou lève une exception si non trouvée
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new CommandeNotFoundException("Commande not found with id: " + id));
        // Met à jour les informations de la commande
        commande.setCommandeStatus(CommandeStatus.PLACED);
        commande.setOrderDate(addCommandeRequest.getOrderDate());
        commande.setTotalAmount(addCommandeRequest.getTotalAmount());
        commande.setCommandeItems(commandeMapper.toEntityList(addCommandeRequest.getCommandeItems(), commande));
        // Enregistre la commande mise à jour et retourne son DTO
        return commandeMapper.toDto(commandeRepository.save(commande));
    }

    @Override
    public void deleteCommande(Long id) {
        meterRegistry.counter("commande-service", "method", "deleteCommande");
        log.debug("Commande with id {} is deled", id);
        log.info("Commande with id {} is deled", id);
        commandeRepository.deleteById(id);

    }
}
