package com.msproduit.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandetemEvent {
    private Long id;

    private Long productId;

    private Integer quantity;

    private Double price;
}
