package fr.mspaiement.ms_paiement.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaiementRequest {
    @JsonProperty("commande_id")
    @Min(value = 0, message = "Commande id must be greater than 0")
    @NotNull(message = "commande id must not be null")
    private Long commandeId;

    @JsonProperty("customer_id")
    @Min(value = 0, message = "Customer id must be greater than 0")
    @NotNull(message = "Customer id must not be null")
    private Long customerId;

    @Min(value = 0, message = "Amount must be greater than 0")
    @NotNull(message = "Amount must not be null")
    private Double amount;
}
