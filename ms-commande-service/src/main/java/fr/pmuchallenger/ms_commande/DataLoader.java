/*
package fr.pmuchallenger.ms_commande;

import fr.pmuchallenger.ms_commande.model.dto.request.ProductAddRequest;
import fr.pmuchallenger.ms_commande.model.dto.request.PartantAddRequest;
import fr.pmuchallenger.ms_commande.services.CommandeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

*/
/**
 * @author: PMU-MANEGR
 *//*

@Component
@Log4j2
public class DataLoader implements CommandLineRunner {
    private final CommandeService courseService;

    @Autowired
    public DataLoader(CommandeService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Crée une course et de partants via les DTO
        ProductAddRequest courseRequest = ProductAddRequest.builder().date(LocalDate.of(2023, 1, 29)).numero(1).nom("prix d'Amérique").partant(buildPartants()).build();
        // je Vérifie si la course est unique (pas de doublons par date et numéro)
        boolean isUnique = courseService.checkCourseUniqueness(courseRequest.getDate(), courseRequest.getNumero());

        if (isUnique) {
            courseService.createCourse(courseRequest);
            System.out.println("Product créée avec succès.");
        } else {
            log.info("Une course existe déjà avec cette date et ce numéro. Aucune action entreprise.");
        }
    }

    private List<PartantAddRequest> buildPartants() {
        return Arrays.asList(PartantAddRequest.builder().numero(1).nom("HORSY DREAM").build(), PartantAddRequest.builder().numero(2).nom("HOOKER BERRY").build(), PartantAddRequest.builder().numero(3).nom("FLAMME DU GOUTIER").build(), PartantAddRequest.builder().numero(4).nom("IDAO DE TILLARD").build());
    }
}
*/
