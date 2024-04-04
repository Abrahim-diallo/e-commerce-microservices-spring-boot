package fr.mspaiement.ms_paiement.mappers;

import fr.mspaiement.ms_paiement.constants.PaiementStatus;
import fr.mspaiement.ms_paiement.model.dto.request.PaiementRequest;
import fr.mspaiement.ms_paiement.model.dto.response.PaiementResponse;
import fr.mspaiement.ms_paiement.model.entities.Paiement;

public class PaiementMapper {

    public static Paiement mapRequestToEntity(PaiementRequest request) {
        if (request == null) {
            return null;
        }
        return Paiement.builder()
                .commandeId(request.getCommandeId())
                .customerId(request.getCustomerId())
                .amount(request.getAmount())
                .paiementStatus(PaiementStatus.PENDING)
                .build();
    }

    public static PaiementResponse mapEntityToResponse(Paiement paiement) {
        if (paiement == null) {
            return null;
        }
        return PaiementResponse.builder()
                .id(paiement.getId())
                .orderId(paiement.getCommandeId())
                .customerId(paiement.getCustomerId())
                .amount(paiement.getAmount())
                .paymentStatus(paiement.getPaiementStatus())
                .build();
    }
}