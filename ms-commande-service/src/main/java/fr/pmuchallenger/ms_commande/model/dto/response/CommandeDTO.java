package fr.pmuchallenger.ms_commande.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.pmuchallenger.ms_commande.model.CommandeStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class CommandeDTO {

    private Long id;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    @JsonProperty("commande_date")
    private LocalDateTime orderDate;

    @JsonProperty("commande_status")
    private CommandeStatus commandeStatus;

    @JsonProperty("commande_items")
    private List<CommandeItemDTO> commandeItems;
}
