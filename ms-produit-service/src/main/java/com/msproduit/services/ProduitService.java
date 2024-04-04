package com.msproduit.services;

import com.msadressesservice.dto.ProduitDTO;
import com.msproduit.dto.request.ProduitAddRequest;
import com.msproduit.dto.request.ProduitUpdateRequest;


import java.util.List;
import java.util.Optional;


public interface ProduitService {

    ProduitDTO createProduit(ProduitAddRequest produitAddRequest);


    ProduitDTO updateProduit(Long productId, ProduitUpdateRequest produitUpdateRequest);

    List<ProduitDTO> getAllProduits();

    Optional<ProduitDTO> getProduitById(Long id);

    void deleteProduit(Long id);
}
