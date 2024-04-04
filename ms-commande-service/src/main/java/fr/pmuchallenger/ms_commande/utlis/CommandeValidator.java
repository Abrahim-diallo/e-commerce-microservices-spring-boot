package fr.pmuchallenger.ms_commande.utlis;


import fr.pmuchallenger.ms_commande.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;


/**
 * @author: pum-manager
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class CommandeValidator {

    private final CommandeRepository courseRepository;


}
