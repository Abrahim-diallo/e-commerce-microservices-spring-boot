package fr.pmuchallenger.ms_commande.services;

import fr.pmuchallenger.ms_commande.model.dto.request.AddCommandeRequest;
import fr.pmuchallenger.ms_commande.model.dto.response.CommandeDTO;

import java.util.List;

/**
 * @author: Ibrahima DIALLO
 */
public interface CommandeService {
    CommandeDTO addCommndae(AddCommandeRequest addCommandeRequest);

    List<CommandeDTO> getAllCommandes();

    CommandeDTO getCommandeById(Long id);

    CommandeDTO updateCommande(Long id, AddCommandeRequest addCommandeRequest);

    void deleteCommande(Long id);

}
