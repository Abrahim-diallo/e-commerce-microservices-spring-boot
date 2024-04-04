package com.msproduit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author: Ibrahima DIALLO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProduitGettAllResponse {
    private String sku;
    private String productName;
    private String productDescription;
    private Boolean status;
    private Double price;


}

