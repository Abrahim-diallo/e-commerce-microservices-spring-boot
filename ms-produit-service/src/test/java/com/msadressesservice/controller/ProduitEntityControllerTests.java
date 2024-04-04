/*
package com.msadressesservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msadressesservice.services.Impl.ProduitServiceImpl;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class ProduitEntityControllerTests {

    // MockMvc pour simuler les requêtes HTTP
    private MockMvc mockMvc;

    // Mock du service d'adresse
    @Mock
    private ProduitServiceImpl adresseService;

    // ObjectMapper pour sérialiser/désérialiser les objets JSON
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Contrôleur à tester, avec injection de dépendances manuelle
    @InjectMocks
    private ProduitController produitController;

    // Configuration initiale pour chaque test
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(produitController).build();
    }

    // Test pour la création d'une adresse avec un objet adresse valide
    @Test
    @DisplayName("Test JUnit Mockito pour la méthode createAdresse")
    public void givenAdresseObject_whenCreateAdresse_thenReturnSavedAdresse() throws Exception {
        // Préparation des données de test
        ProduitDTO adresseDTO = new ProduitDTO();
        adresseDTO.setId(2L);
        adresseDTO.setRue("valDefontainay");
        adresseDTO.setEmail("lodiaakhalilllll@gmail.com");
        adresseDTO.setVille("Noisy-le-Grand");
        adresseDTO.setPays("France");
        adresseDTO.setRegion("Paris");
        adresseDTO.setTelephone("788888888888");
        adresseDTO.setCommune("Le Grand");
        adresseDTO.setCodePostal("93130");
        // Configuration du comportement mock
        given(adresseService.createAdresse(adresseDTO)).willReturn(adresseDTO);
        // Exécution de la requête de test et vérification des assertions
        mockMvc.perform(post("/api/V1/adresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(adresseDTO)))
                .andDo(print())
                .andExpect(status().isCreated()) // Vérification du statut HTTP 201 (Created)
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.rue").value("valDefontainay"))
                .andExpect(jsonPath("$.email").value("lodiaakhalilllll@gmail.com"))
                .andExpect(jsonPath("$.ville").value("Noisy-le-Grand"))
                .andExpect(jsonPath("$.pays").value("France"))
                .andExpect(jsonPath("$.region").value("Paris"))
                .andExpect(jsonPath("$.telephone").value("788888888888"))
                .andExpect(jsonPath("$.commune").value("Le Grand"))
                .andExpect(jsonPath("$.codePostal").value("93130"));
    }

    // JUnit test for Get all Adresse Rest api
    @Test
    @DisplayName("Get all Adresse Rest")
    public void givenListOfAdresse_whenGetAllAdresses_thenReturnAdressesList() throws Exception {
        // Arrange - Setup
        List<ProduitDTO> adresseEntityLOA = new ArrayList<>();
        adresseEntityLOA.add(ProduitDTO.builder()
                        .id(1L)
                        .rue("valDefontainay")
                        .email("lodiaakhalilllll@gmail.com")
                        .ville("Noisy-le-Grand")
                        .pays("FRANCE")
                        .codePostal("93130")
                        .commune("Noisy")
                        .telephone("0645326596")
                        .region("Paris")
                        .build());
        adresseEntityLOA.add(ProduitDTO.builder()
                        .id(1L)
                        .rue("Niaye Tioker")
                        .email("lodiaakhalilllll@gmail.com")
                        .ville("Grand DAKAR")
                        .pays("SENEGAL")
                        .codePostal("11111")
                        .commune("DAKAR")
                        .telephone("0645326596")
                        .region("DAKAR")
                        .build());
        given(adresseService.getAllAdresses()).willReturn(adresseEntityLOA);
        // Act - Perform the action
        ResultActions resultActions = mockMvc.perform(get("/api/V1/adresses").contentType(MediaType.APPLICATION_JSON));
        // Assert - Verify the output
        resultActions
                .andExpect(status().isOk()) // Verify HTTP status is OK
                .andDo(print()) // Print request/response for debugging purposes
                .andExpect((ResultMatcher) jsonPath("$.size()", is(adresseEntityLOA.size()))); // Verify JSON response size matches expected
    }
    // Positive scenario- valid adresse id
    // JUnit test for getting the adresses by id
    @Test
    @DisplayName("Get adresses by id")
    public void givenAdresseId_whenGetAdresseById_thenReturnAdresseObject() throws Exception {
        // given - precondition or setup
        long adresseId = 1L;
        given(adresseService.getAdresseById(adresseId)).willReturn(Optional.of(ProduitDTO.builder()
                        .id(1L)
                        .rue("Niaye Tioker")
                        .email("lodiaakhalilllll@gmail.com")
                        .ville("Grand DAKAR")
                        .pays("SENEGAL")
                        .codePostal("11111")
                        .commune("DAKAR")
                        .telephone("0645326596")
                        .region("DAKAR")
                        .build()));
        // when -  action or the behaviour that we are going test
        ResultActions resultActions = mockMvc.perform(get("/api/V1/adresses/" + adresseId).contentType(MediaType.APPLICATION_JSON));
        // then - verify the output
        resultActions
              .andExpect(status().isOk()) // Verify HTTP status is OK
              .andDo(print()) // Print request/response for debugging purposes
              .andExpect((ResultMatcher) jsonPath("$.rue", is("Niaye Tioker"))); // Verify JSON response size matches expected
        //logger.info(" successful ")
    }
    // Test de scénario négatif - ID d'adresse valide
    // JUnit test for getting the adresses by id
    @Test
    @DisplayName("Get adresses by id")
    public void givenInvalidAdresseId_whenGetAdresseById_thenReturnEmpty() throws Exception {
        // given - Préparation des données
        long adresseId = 1L;
        given(adresseService.getAdresseById(adresseId)).willReturn(Optional.empty());
        // when - Exécution de la requête
        ResultActions resultActions = mockMvc.perform(get("/api/V1/adresses/" + adresseId)
                .contentType(MediaType.APPLICATION_JSON));
        // then - Vérification de la réponse
        resultActions
                .andExpect(status().isNotFound()) // Vérifie que le statut HTTP est NotFound (404)
                .andDo(print()); // Affiche ou imprime la requête/réponse pour débogage
    }


}
*/
