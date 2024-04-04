package fr.pmuchallenger.ms_commande.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/*
 * @author: Ibrahima DIALLO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCommandeRequest {
    @NotNull(message = "Customer Id cannot be null")
    @JsonProperty("customer_id")
    private Long customerId;

    @DecimalMin(value = "0.0", message = "Total amount must be greater than or equal to 0")
    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    @NotNull(message = "Commande date cannot be null")
    @JsonProperty("commande_date")
    private LocalDateTime orderDate;

    @NotEmpty(message = "Commande Items cannot be empty")
    @Valid
    @JsonProperty("commande_items")
    private List<AddCommandeRequest> commandeItems;

}

