package fr.mspaiement.ms_paiement.eventkafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaiementEvent {
    private Long paiementId;
    private Long commandeId;
    private Long customerId;
    private Double amount;
}
