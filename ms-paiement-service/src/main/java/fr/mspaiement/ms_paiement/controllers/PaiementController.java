package fr.mspaiement.ms_paiement.controllers;

import fr.mspaiement.ms_paiement.model.dto.request.PaiementRequest;
import fr.mspaiement.ms_paiement.model.dto.response.PaiementResponse;
import fr.mspaiement.ms_paiement.services.PaiementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/paiements")
@Api(value = "Paiements", tags = {"Paiements"})
public class PaiementController {
    private final PaiementService paiementService;

    @PostMapping
    @ApiOperation(value = "Créer un paiement", notes = "Crée un nouveau paiement")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Paiement créé avec succès"),
            @ApiResponse(code = 400, message = "Requête invalide")
    })
    public ResponseEntity<PaiementResponse> createPaiements(@RequestBody @Valid PaiementRequest paiementRequest) {
        log.info("Requête de création de paiement : {}", paiementRequest.getCustomerId());
        return new ResponseEntity<>(paiementService.createPaiement(paiementRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaiementResponse> getPaiementById(@PathVariable("id") Long id){
        log.info("get paiment by id : {}", id);
        return new ResponseEntity<>(paiementService.getPaymentById(id), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    @ApiOperation(value = "Obtenir les paiements par ID client", notes = "Récupère la liste des paiements d'un client spécifique")
    public ResponseEntity<List<PaiementResponse>> getPaiementsByCustomerId(@PathVariable("id") Long id) {
        log.info("Récupérer les paiements par ID client : {}", id);
        return new ResponseEntity<>(paiementService.getPaiementsByCustomerId(id), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    @ApiOperation(value = "Mettre à jour un paiement", notes = "Met à jour un paiement existant")
    public ResponseEntity<PaiementResponse> updatePaiement(
            @ApiParam(value = "ID du paiement à mettre à jour", required = true) @PathVariable("id") Long id,
            @RequestBody @Valid PaiementRequest paiementRequest) {
        log.info("Mettre à jour le paiement par ID : {}", id);
        return new ResponseEntity<>(paiementService.updatePaiement(id, paiementRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Supprimer un paiement", notes = "Supprime un paiement existant")
    public ResponseEntity<?> deletePaiement(@PathVariable("id") Long id) {
        log.info("Supprimer le paiement par ID : {}", id);
        paiementService.deletePaiement(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}