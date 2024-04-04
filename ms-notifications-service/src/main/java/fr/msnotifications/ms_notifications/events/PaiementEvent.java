package fr.msnotifications.ms_notifications.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class PaiementEvent implements Serializable {
    private Long paiementId;
    private Long commandeId;
    private Long customerId;
    private Double amount;
}
