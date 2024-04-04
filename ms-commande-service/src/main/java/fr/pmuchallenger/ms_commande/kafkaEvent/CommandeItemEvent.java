package fr.pmuchallenger.ms_commande.kafkaEvent;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CommandeItemEvent {
    private Long id;
    private Long productId;
    private Integer quantity;
    private Double price;
}
