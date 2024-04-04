package fr.pmuchallenger.ms_commande.kafkaEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommndePlacedEvent implements Serializable {
    private Long CommandeId;
    private Long cusomerId;
    private BigDecimal totalAmount;
    private List<CommandeItemEvent> commandeItems;
}
