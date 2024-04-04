package fr.pmuchallenger.ms_commande.controller;

import fr.pmuchallenger.ms_commande.model.dto.request.AddCommandeRequest;
import fr.pmuchallenger.ms_commande.model.dto.response.CommandeDTO;
import fr.pmuchallenger.ms_commande.services.CommandeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @author: Ibrahima DIALLO
 */
@Log4j2
@RestController
@RequestMapping("/api/v1/commandes")
@RequiredArgsConstructor
public class CommandeController {
    private final CommandeService commandeService;

    //----create commande methode via post
    @PostMapping
    public ResponseEntity<CommandeDTO> createCommande(@RequestBody @Valid AddCommandeRequest addCommandeRequest){
        log.debug("/api/v1/commandes is created with POST method");
        return new ResponseEntity<>(commandeService.addCommndae(addCommandeRequest), HttpStatus.CREATED);
    }
    //----gett All commands----
    @GetMapping
    public ResponseEntity<List<CommandeDTO>> getAllCommandes(){
        log.debug("/api/v1/commandes/ is called with GET method");
        return new ResponseEntity<>(commandeService.getAllCommandes(), HttpStatus.OK);
    }
    //----get commande by ID commande---
    @GetMapping("/{}id")
    public ResponseEntity<CommandeDTO> getCommandeById(@PathVariable("id") Long id){
        log.debug("/api/v1/commandes/{} is called wiht GET mehod");
        return new ResponseEntity<>(commandeService.getCommandeById(id), HttpStatus.OK);
    }
    //---Update commande by id and AddCommandeRequest--
    @PutMapping("/{id}")
    public ResponseEntity<CommandeDTO> updateComande(@PathVariable("id") Long id, @RequestBody @Valid AddCommandeRequest addCommandeRequest){
        log.debug("/api/v1/commandes/{} is called wiht PUT method");
        return new ResponseEntity<>(commandeService.updateCommande(id, addCommandeRequest), HttpStatus.OK);
    }
    //---DELETE COMMANDE BY ID COMMANDE--
    @DeleteMapping("/{}id")
    public ResponseEntity<?> deleteCommandeById(@PathVariable("id") Long id){
        log.debug("/api/v1/commandes/{} is called with DELETE method");
        commandeService.deleteCommande(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
