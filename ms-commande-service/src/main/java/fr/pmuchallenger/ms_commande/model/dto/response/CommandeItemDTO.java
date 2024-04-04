package fr.pmuchallenger.ms_commande.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommandeItemDTO {
    private Long id;
    @JsonProperty("product_id")
    private Long productId;
    private Integer quantity;
    private Double price;
}
