package fr.mspaiement.ms_paiement.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.mspaiement.ms_paiement.constants.PaiementStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaiementResponse {
    private Long id;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("customer_id")
    private Long customerId;

    private Double amount;

    @JsonProperty("paiement_status")
    private PaiementStatus paymentStatus;

}
