package com.msproduit.services.Impl;

import com.msadressesservice.dto.ProduitDTO;
import com.msproduit.Mappers.ProduitConverter;
import com.msproduit.dto.request.ProduitAddRequest;
import com.msproduit.dto.request.ProduitUpdateRequest;
import com.msproduit.entities.ErrorDetails;
import com.msproduit.entities.ProduitEntity;
import com.msproduit.exceptions.DuplicateProductException;
import com.msproduit.exceptions.GlobalExceptionHandler;
import com.msproduit.exceptions.ProduitAPIException;
import com.msproduit.exceptions.ResourceNotFoundException;
import com.msproduit.repositories.ProduitRepository;
import com.msproduit.services.ProduitService;
import com.msproduit.utlis.ProduitValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
@Validated
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;
    private final ProduitConverter produitConverter;
    private final ProduitValidator produitValidator;
    private final GlobalExceptionHandler globalExceptionHandler;

    @Override
    public ProduitDTO createProduit(ProduitAddRequest produitAddRequest) {
        try {
            produitValidator.validateProduitAddRequest(produitAddRequest);
            log.info("Creating new product: {}", produitAddRequest);
            // Vérifier l'existence du produit avec le SKU donné
            if (produitRepository.existsBySku(produitAddRequest.getSku())) {
                log.error("Product with SKU {} already exists", produitAddRequest.getSku());
                throw new DuplicateProductException("Product with SKU " + produitAddRequest.getSku() + " already exists");
            }
            ProduitEntity produit = produitConverter.convertToEntity(produitAddRequest);
            ProduitEntity savedProduit = produitRepository.save(produit);
            return produitConverter.convertToDto(savedProduit);
        } catch (DuplicateProductException e) {
            log.error("Error creating product: {}", e.getMessage());
            throw e; // Re-lance DuplicateProductException pour qu'elle soit gérée par GlobalExceptionHandler
        } catch (Exception e) {
            log.error("Error creating product: {}", e.getMessage());
            throw new ProduitAPIException("Failed to create product", e.getMessage());
        }
    }



    @Override
    public ProduitDTO updateProduit(Long productId, ProduitUpdateRequest produitUpdateRequest) {
        try {
            produitValidator.validateProduitUpdateRequest(productId, produitUpdateRequest);
            log.info("Updating product with ID {}: {}", productId, produitUpdateRequest);
            ProduitEntity existingProduit = produitRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
            produitConverter.updateEntity(existingProduit, produitUpdateRequest);
            ProduitEntity updatedProduit = produitRepository.save(existingProduit);
            return produitConverter.convertToDto(updatedProduit);
        } catch (Exception e) {
            log.error("Error updating product with ID {}: {}", productId, e.getMessage());
            throw new ProduitAPIException("Failed to update product", e.getMessage());
        }
    }

    @Override
    public List<ProduitDTO> getAllProduits() {
        try {
            log.info("Getting all products");
            List<ProduitEntity> produits = produitRepository.findAll();
            return produitConverter.convertToDtoList(produits);
        } catch (Exception e) {
            log.error("Error getting all products: {}", e.getMessage());
            throw new ProduitAPIException("Failed to retrieve products", e.getMessage());
        }
    }

    @Override
    public Optional<ProduitDTO> getProduitById(Long id) {
        try {
            log.info("Getting product by ID: {}", id);
            Optional<ProduitEntity> optionalProduit = produitRepository.findById(id);
            return optionalProduit.map(produitConverter::convertToDto);
        } catch (Exception e) {
            log.error("Error getting product with ID {}: {}", id, e.getMessage());
            throw new ProduitAPIException("Failed to retrieve product", e.getMessage());
        }
    }

    @Override
    public void deleteProduit(Long id) {
        try {
            log.info("Deleting product with ID: {}", id);
            ProduitEntity existingProduit = produitRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
            produitRepository.delete(existingProduit);
        } catch (Exception e) {
            log.error("Error deleting product with ID {}: {}", id, e.getMessage());
            throw new ProduitAPIException("Failed to delete product", e.getMessage());
        }
    }
}
