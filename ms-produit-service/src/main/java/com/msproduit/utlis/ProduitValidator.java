package com.msproduit.utlis;

import com.msproduit.dto.request.ProduitAddRequest;
import com.msproduit.dto.request.ProduitUpdateRequest;
import com.msproduit.exceptions.ProduitAPIException;
import com.msproduit.repositories.ProduitRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


@Component
@Validated
@RequiredArgsConstructor
public class ProduitValidator {

    private final ProduitRepository produitRepository;

    public void validateProduitAddRequest(@Valid ProduitAddRequest produitAddRequest) {
        if (produitAddRequest == null) {
            throw new ProduitAPIException("RequestValidation", "La demande d'ajout de produit est nulle.");
        }
        if (produitAddRequest.getSku() == null || produitAddRequest.getSku().isEmpty()) {
            throw new ProduitAPIException("RequestValidation", "Le SKU du produit est requis.");
        }
    }

    public void validateProduitUpdateRequest(@NotNull Long productId, @Valid ProduitUpdateRequest produitUpdateRequest) {
        if (!produitRepository.existsById(productId)) {
            throw new ProduitAPIException("ProduitNotFound", "Aucun produit trouvé avec l'identifiant: " + productId);
        }
    }
}
