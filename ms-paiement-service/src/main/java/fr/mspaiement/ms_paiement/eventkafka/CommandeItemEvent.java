package fr.mspaiement.ms_paiement.eventkafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeItemEvent {
    private Long id;
    private Long productId;
    private Integer quantity;
    private Double price;
}
