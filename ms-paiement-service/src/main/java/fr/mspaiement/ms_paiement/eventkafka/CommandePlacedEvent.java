package fr.mspaiement.ms_paiement.eventkafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandePlacedEvent {
    private Long commandeId;
    private Long customerId;
    private BigDecimal totalAmount;
    private List<CommandeItemEvent> commandeItems;
}
