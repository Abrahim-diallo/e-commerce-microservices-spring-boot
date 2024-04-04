package fr.mspaiement.ms_paiement.services.Impl;

import fr.mspaiement.ms_paiement.exceptions.PaiementNotFoundException;
import fr.mspaiement.ms_paiement.mappers.PaiementMapper;
import fr.mspaiement.ms_paiement.model.dto.request.PaiementRequest;
import fr.mspaiement.ms_paiement.model.dto.response.PaiementResponse;
import fr.mspaiement.ms_paiement.model.entities.Paiement;
import fr.mspaiement.ms_paiement.repository.PaiementRepository;
import fr.mspaiement.ms_paiement.services.PaiementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaiementServiceImpl implements PaiementService {
    private final PaiementRepository paiementRepository;

    @Override
    public PaiementResponse createPaiement(PaiementRequest paiementRequest) {
        // logs=> message d'information indiquant la réception de la requête de création de paiement
        log.info("Requête de création de paiement : {}", paiementRequest);
        try {
            // On mappe les données de la requête en une entité Paiement
            Paiement paiement = PaiementMapper.mapRequestToEntity(paiementRequest);
            // Enregistrement le paiement dans la base de données
            Paiement savedPaiement = paiementRepository.save(paiement);
            // Vérification si le paiement a été enregistré avec succès
            if (savedPaiement != null) {
                return PaiementMapper.mapEntityToResponse(savedPaiement);
            } else {
                // On gére le cas où le paiement n'a pas été enregistré avec succès
                throw new RuntimeException("Le paiement n'a pas été enregistré avec succès.");
            }
        } catch (DataIntegrityViolationException e) {
            //  logs l'exception spécifique liée à une violation de contrainte d'intégrité
            log.error("####Erreur lors de la création du paiement : Contrainte d'intégrité violée", e);
            throw new RuntimeException("Erreur lors de la création du paiement : Contrainte d'intégrité violée", e);
        } catch (Exception e) {
            // On gére et loggue toute autre exception générique survenue lors de la création du paiement
            log.error("Erreur lors de la création du paiement", e);
            throw new RuntimeException("Une erreur s'est produite lors de la création du paiement: " + e.getMessage(), e);
        }
    }

    @Override
    public PaiementResponse updatePaiement(Long id, PaiementRequest paiementRequest) {
        log.info("Mise à jour du paiement avec l'identifiant : {}", id);
        try {
            Paiement paiementToUpdate = paiementRepository.findById(id)
                    .orElseThrow(() -> new PaiementNotFoundException("Paiement introuvable avec l'identifiant : " + id));

            updatePaiementDetails(paiementToUpdate, paiementRequest);
            Paiement updatedPaiement = paiementRepository.save(paiementToUpdate);

            return PaiementMapper.mapEntityToResponse(updatedPaiement);
        } catch (PaiementNotFoundException e) {
            log.error("Paiement introuvable lors de la mise à jour", e);
            throw e;
        } catch (DataIntegrityViolationException e) {
            // On gere l'exception spécifique liée à une violation de contrainte d'intégrité
            log.error("Erreur lors de la mise à jour du paiement : Contrainte d'intégrité violée", e);
            throw new RuntimeException("Erreur lors de la mise à jour du paiement : Contrainte d'intégrité violée", e);
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour du paiement", e);
            throw new RuntimeException("Erreur lors de la mise à jour du paiement", e);
        }
    }

    @Override
    public void deletePaiement(Long id) {
        try {
            Optional<Paiement> paiementOptional = paiementRepository.findById(id);

            if (paiementOptional.isPresent()) {
                paiementRepository.deleteById(id);
            } else {
                // On gere le cas où aucun paiement n'est trouvé pour l'ID donné
                throw new PaiementNotFoundException("Aucun paiement trouvé pour l'ID : " + id);
            }
        } catch (EmptyResultDataAccessException e) {
            // On gere l'exception spécifique lorsque aucun enregistrement n'est trouvé pour la suppression
            throw new PaiementNotFoundException("Aucun paiement trouvé pour l'ID : " + id);
        } catch (Exception e) {
            log.error("Erreur lors de la suppression du paiement avec l'ID : " + id, e);
            throw new RuntimeException("Erreur lors de la suppression du paiement avec l'ID : " + id, e);
        }
    }

    @Override
    public List<PaiementResponse> getAllPaiement() {
        try {
            List<Paiement> paiements = paiementRepository.findAll();

            if (!paiements.isEmpty()) {
                return paiements.stream()
                        .map(PaiementMapper::mapEntityToResponse)
                        .collect(Collectors.toList());
            } else {
                //  le cas où aucun paiement n'est trouvé
                throw new PaiementNotFoundException("Aucun paiement trouvé.");
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de tous les paiements", e);
            throw new RuntimeException("Erreur lors de la récupération de tous les paiements", e);
        }
    }

    /**
     * @param id 
     * @return
     */
    @Override
    public PaiementResponse getPaymentById(Long id) {
        try {
            Optional<Paiement> paiementOptional = paiementRepository.findById(id);
            if (paiementOptional.isPresent()) {
                Paiement paiement = paiementOptional.get();
                return PaiementMapper.mapEntityToResponse(paiement);
            } else {
                // on gere le cas où aucun paiement n'est trouvé pour l'ID donné
                throw new PaiementNotFoundException("Aucun paiement trouvé pour l'ID : " + id);
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération du paiement par ID : " + id, e);
            throw new RuntimeException("Erreur lors de la récupération du paiement par ID : " + id, e);
        }
    }

    @Override
    public List<PaiementResponse> getPaiementsByCustomerId(Long customerId) {
        try {
            List<Paiement> paiements = paiementRepository.findByCustomerId(customerId);

            if (!paiements.isEmpty()) {
                return paiements.stream()
                        .map(PaiementMapper::mapEntityToResponse)
                        .collect(Collectors.toList());
            } else {
                // On gere le cas où aucun paiement n'est trouvé pour le client donné
                throw new PaiementNotFoundException("Aucun paiement trouvé pour le client avec l'ID : " + customerId);
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des paiements par ID client", e);
            throw new RuntimeException("Erreur lors de la récupération des paiements par ID client", e);
        }
    }

    private void updatePaiementDetails(Paiement paiement, PaiementRequest paiementRequest) {
        paiement.setAmount(paiementRequest.getAmount());
        paiement.setCustomerId(paiementRequest.getCustomerId());
        paiement.setCommandeId(paiementRequest.getCommandeId());
    }
}