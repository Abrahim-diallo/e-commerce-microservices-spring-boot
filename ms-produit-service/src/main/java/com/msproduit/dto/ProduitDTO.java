package com.msadressesservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
 * @author: Ibrahima DIALLO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProduitDTO implements Serializable {
    private String sku;
    private String productName;
    private String productDescription;
    private Boolean status;
    private Double price;


}
