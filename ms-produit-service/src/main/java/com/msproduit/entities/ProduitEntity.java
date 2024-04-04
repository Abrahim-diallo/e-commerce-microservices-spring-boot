package com.msproduit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "produits")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String sku;

    private String productName;

    private String productDescription;

    private Boolean status;

    private Double price;


}

