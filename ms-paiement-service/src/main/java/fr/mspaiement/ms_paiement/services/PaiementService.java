package fr.mspaiement.ms_paiement.services;

import fr.mspaiement.ms_paiement.model.dto.request.PaiementRequest;
import fr.mspaiement.ms_paiement.model.dto.response.PaiementResponse;

import java.util.List;

public interface PaiementService {

    PaiementResponse createPaiement(PaiementRequest paiementRequest);

    PaiementResponse updatePaiement(Long id, PaiementRequest paiementRequest);

    void deletePaiement(Long id);

    List<PaiementResponse> getAllPaiement();

    PaiementResponse getPaymentById(Long id);
    List<PaiementResponse> getPaiementsByCustomerId(Long customerId);

}
