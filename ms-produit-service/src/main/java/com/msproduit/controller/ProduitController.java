package com.msproduit.controller;

import com.msadressesservice.dto.ProduitDTO;
import com.msproduit.dto.request.ProduitAddRequest;
import com.msproduit.dto.request.ProduitUpdateRequest;
import com.msproduit.exceptions.ProduitAPIException;
import com.msproduit.services.ProduitService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author: Ibrahima DIALLO
 */
@RestController
@RequestMapping(value = "/api/V1/produits", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Log4j2
public class ProduitController {

    private final ProduitService produitService;

    private static final String SUCCESS_INSERT = "Produit inséré avec succès";
    private static final String SUCCESS_UPDATE = "Produit mis à jour avec succès";
    private static final String SUCCESS_DELETE = "Produit supprimé avec succès";
    private static final String ERROR_NOT_FOUND = "Produit non trouvé";

    @PostMapping("/ajouter")
    @Operation(summary = "Ajouter un nouveau produit", description = "Permet d'ajouter un nouveau produit à la base de données")
    public ResponseEntity<ProduitDTO> addProduit(@Valid @RequestBody ProduitAddRequest produitAddRequest) {
        try {
            ProduitDTO produitDTO = produitService.createProduit(produitAddRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(produitDTO);
        } catch (ProduitAPIException e) {
            log.error("Erreur lors de l'ajout du produit : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProduitDTO());
        }
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Mettre à jour un produit", description = "Permet de mettre à jour les informations d'un produit existant")
    public ResponseEntity<ProduitDTO> updateProduit(@PathVariable Long productId, @Valid @RequestBody ProduitUpdateRequest produitUpdateRequest) {
        try {
            ProduitDTO updatedProduitDTO = produitService.updateProduit(productId, produitUpdateRequest);
            return ResponseEntity.ok().body(updatedProduitDTO);
        } catch (ProduitAPIException e) {
            log.error("Erreur lors de la mise à jour du produit : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProduitDTO());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un produit par ID", description = "Permet d'obtenir les informations d'un produit en fonction de son ID")
    public ResponseEntity<ProduitDTO> getProduitById(@PathVariable("id") Long id) {
        Optional<ProduitDTO> produitDTO = produitService.getProduitById(id);
        return produitDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProduitDTO()));
    }

    @GetMapping("/tous")
    @Operation(summary = "Obtenir tous les produits", description = "Permet d'obtenir la liste de tous les produits disponibles")
    public ResponseEntity<List<ProduitDTO>> getAllProduits() {
        List<ProduitDTO> produits = produitService.getAllProduits();
        return ResponseEntity.ok().body(produits);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un produit", description = "Permet de supprimer un produit existant en fonction de son ID")
    public ResponseEntity<String> deleteProduit(@PathVariable("id") Long id) {
        try {
            produitService.deleteProduit(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(SUCCESS_DELETE);
        } catch (ProduitAPIException e) {
            log.error("Erreur lors de la suppression du produit : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
