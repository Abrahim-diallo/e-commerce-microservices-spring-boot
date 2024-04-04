package fr.pmuchallenger.ms_commande.mappers;

import fr.pmuchallenger.ms_commande.kafkaEvent.CommandeItemEvent;
import fr.pmuchallenger.ms_commande.model.dto.request.AddCommandeRequest;
import fr.pmuchallenger.ms_commande.model.dto.response.CommandeDTO;
import fr.pmuchallenger.ms_commande.model.dto.response.CommandeItemDTO;
import fr.pmuchallenger.ms_commande.model.entities.Commande;
import fr.pmuchallenger.ms_commande.model.entities.CommandeItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommandeMapper {

    private final ModelMapper modelMapper;

    // Convertir une entité Commande en DTO
    public CommandeDTO toDto(Commande commande) {
        return modelMapper.map(commande, CommandeDTO.class);
    }

    // Convertir une liste d'entités CommandeItem en une liste de DTO
    public List<CommandeItemDTO> toDtoList(List<CommandeItem> commandeItems) {
        return commandeItems.stream()
                .map(commandeItem -> modelMapper.map(commandeItem, CommandeItemDTO.class))
                .collect(Collectors.toList());
    }

    // Convertir une requête d'ajout de commande en entité Commande
    public Commande toEntity(AddCommandeRequest addCommandeRequest) {
        return modelMapper.map(addCommandeRequest, Commande.class);
    }

    // Convertir une liste de requêtes d'ajout d'éléments de commande en une liste d'entités CommandeItem
    public List<CommandeItem> toEntityList(@NotEmpty(message = "Commande Items cannot be empty") @Valid List<AddCommandeRequest> addCommandeItemRequests, Commande commande) {
        return addCommandeItemRequests.stream()
                .map(addCommandeItemRequest -> {
                    CommandeItem commandeItem = modelMapper.map(addCommandeItemRequest, CommandeItem.class);
                    commandeItem.setCommande(commande);
                    return commandeItem;
                })
                .collect(Collectors.toList());
    }

    // Event
    public CommandeItemEvent toCommandeItemEvent(CommandeItem commandeItem) {
        return CommandeItemEvent.builder()
                .id(commandeItem.getId())
                .productId(commandeItem.getProductId())
                .quantity(commandeItem.getQuantity())
                .price(commandeItem.getPrice())
                .build();
    }

    public List<CommandeItemEvent> toCommandeItemEventList(List<CommandeItem> commandeItems) {
        return commandeItems.stream()
                .map(this::toCommandeItemEvent)
                .collect(Collectors.toList());
    }
}
