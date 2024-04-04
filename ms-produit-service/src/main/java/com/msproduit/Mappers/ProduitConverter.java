package com.msproduit.Mappers;

import com.msadressesservice.dto.ProduitDTO;


import com.msproduit.dto.request.ProduitAddRequest;
import com.msproduit.dto.request.ProduitUpdateRequest;
import com.msproduit.entities.ProduitEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitConverter {

    public ProduitDTO convertToDto(ProduitEntity produit) {
        ProduitDTO produitDTO = new ProduitDTO();
        produitDTO.setSku(produit.getSku());
        produitDTO.setProductName(produit.getProductName());
        produitDTO.setProductDescription(produit.getProductDescription());
        produitDTO.setStatus(produit.getStatus());
        produitDTO.setPrice(produit.getPrice());
        return produitDTO;
    }

    public ProduitEntity convertToEntity(@NotNull @Valid ProduitAddRequest produitDTO) {
        ProduitEntity produit = new ProduitEntity();
        produit.setSku(produitDTO.getSku());
        produit.setProductName(produitDTO.getProductName());
        produit.setProductDescription(produitDTO.getProductDescription());
        produit.setStatus(produitDTO.getStatus());
        produit.setPrice(produitDTO.getPrice());
        return produit;
    }

    public void updateEntity(@NotNull ProduitEntity produitEntity, @NotNull @Valid ProduitUpdateRequest produitUpdateRequest) {
        produitEntity.setSku(produitUpdateRequest.getSku());
        produitEntity.setProductName(produitUpdateRequest.getProductName());
        produitEntity.setProductDescription(produitUpdateRequest.getProductDescription());
        produitEntity.setStatus(produitUpdateRequest.getStatus());
        produitEntity.setPrice(produitUpdateRequest.getPrice());
    }

    public List<ProduitDTO> convertToDtoList(List<ProduitEntity> produits) {
        return produits.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
