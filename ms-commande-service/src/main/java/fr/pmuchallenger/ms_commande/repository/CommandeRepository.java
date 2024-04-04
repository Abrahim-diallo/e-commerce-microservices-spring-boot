package fr.pmuchallenger.ms_commande.repository;

import fr.pmuchallenger.ms_commande.model.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * @author: Ibrahima DIALLO
 */
@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    Optional<Commande> findById(Commande commande);
}

