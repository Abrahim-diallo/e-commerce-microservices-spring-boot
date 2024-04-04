package com.msproduit.dto.request;

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
public class ProduitAddRequest {
    private String sku;
    private String productName;
    private String productDescription;
    private Boolean status;
    private Double price;


}

