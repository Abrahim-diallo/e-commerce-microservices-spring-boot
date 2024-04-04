package fr.mspaiement.ms_paiement.repository;

import fr.mspaiement.ms_paiement.model.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    Optional<Paiement> findById(Long paiementId);

    List<Paiement> findByCustomerId(Long customerId); // Modifier le type de retour pour correspondre à List<Paiement>
}