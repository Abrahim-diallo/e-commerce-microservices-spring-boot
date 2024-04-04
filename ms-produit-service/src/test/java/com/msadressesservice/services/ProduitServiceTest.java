/*
package com.msadressesservice.services;

import com.msadressesservice.entities.ProduitEntity;
import com.msadressesservice.exceptions.ProduitAPIException;
import com.msadressesservice.repositories.ProduitRepository;
import com.msadressesservice.services.Impl.ProduitServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Log4j2
public class ProduitServiceTest {
    @InjectMocks
    private ProduitServiceImpl produitServiceImpl;
    @Mock
    private ProduitRepository adresseRepository;
    @Mock
    private AdresseMapperImpl adresseMapperImpl;

    private ProduitDTO adresseDTO;
    private ProduitEntity adresseEntity;
    private ProduitDTO expectedSavedAdresseDTO;

    @BeforeEach
    public void setup() {
        adresseDTO = ProduitDTO.builder().id(1L).rue("jura").email("toto@gmail.com").ville("Noisy-le-Grand").pays("France").codePostal("93130").commune("LeGrand").region("Paris").telephone("23886898985").build();
        adresseEntity = new ProduitEntity(); // Pour le mock de conversion
        expectedSavedAdresseDTO = ProduitDTO.builder().id(2L).rue("jura").email("toto@gmail.com").ville("Noisy-le-Grand").pays("France").codePostal("93130").commune("LeGrand").region("Paris").telephone("23886898985").build();
    }

    @Test
    @DisplayName("Test JUnit Mockito pour la méthode createAdresse")
    public void givenAdresseObject_whenSaveAdresse_thenReturnAdresseObject() {
        // Given
        given(adresseMapperImpl.toEntity(adresseDTO)).willReturn(adresseEntity);
        given(adresseRepository.findByEmail(adresseDTO.getEmail())).willReturn(Optional.empty());
        given(adresseRepository.save(adresseEntity)).willReturn(adresseEntity);
        given(adresseMapperImpl.toDto(adresseEntity)).willReturn(expectedSavedAdresseDTO);
        // When
        ProduitDTO savedAdresseDTO = produitServiceImpl.createAdresse(adresseDTO);
        // Then
        assertThat(savedAdresseDTO).isNotNull(); // Vérifie si l'objet retourné n'est pas null
        assertThat(savedAdresseDTO).usingRecursiveComparison().isEqualTo(expectedSavedAdresseDTO);
        // Verify
        verify(adresseRepository).save(adresseEntity);
        verify(adresseMapperImpl).toDto(adresseEntity);
        verify(adresseRepository).findByEmail(adresseDTO.getEmail());
        verify(adresseMapperImpl).toEntity(adresseDTO);
        // Logging
        log.info(savedAdresseDTO.toString());
    }

    @Test
    @DisplayName("Test JUnit Mockito pour la méthode with throws exception")
    public void givenExistingEmail_whenSaveAdresse_thenThrowsExceptions() {
        // Given
        given(adresseMapperImpl.toEntity(adresseDTO)).willReturn(adresseEntity);
        given(adresseRepository.findByEmail(adresseDTO.getEmail())).willReturn(Optional.of(adresseEntity)); //pour retourner une adresse existante
        // When & Then
        assertThrows(ProduitAPIException.class, () -> produitServiceImpl.createAdresse(adresseDTO));
        // Verify
        verify(adresseRepository, never()).save(any(ProduitEntity.class));
        // Logging
        log.info("ProduitAPIException was thrown as expected");
    }

    // JUnit test for getAllAdresse method
    @Test
    @DisplayName("Test JUnit Mockito pour la méthode getAllAdresses")
    public void givenAdresseList_whenGetAllAdresses_thenReturnAdressesList() {
        // given - préconditions ou configuration
        adresseEntity = new ProduitEntity();
        adresseEntity.setId(2L);
        adresseEntity.setRue("jura");
        adresseEntity.setEmail("totor@gmail.com");
        adresseEntity.setVille("Noisy-lee-Grrand");
        adresseEntity.setPays("Frarnce");
        adresseEntity.setCodePostal("8893130");
        adresseEntity.setCommune("LeGrrand");
        adresseEntity.setRegion("Parris");
        adresseEntity.setTelephone("788888888888");
        // Mock du comportement
        given(adresseRepository.findAll()).willReturn(List.of(adresseEntity));
        given(adresseMapperImpl.toDto(adresseEntity)).willReturn(expectedSavedAdresseDTO);
        // when - exécute l'opération réelle
        List<ProduitDTO> adressesDTO = produitServiceImpl.getAllAdresses();
        // then - vérifie la sortie
        assertThat(adressesDTO).isNotNull(); // Vérifie que la liste retournée n'est pas nulle
        assertThat(adressesDTO.size()).isEqualTo(1); // Vérifie la taille de la liste retournée
        assertThat(adressesDTO.get(0)).usingRecursiveComparison().isEqualTo(expectedSavedAdresseDTO); // Vérifie le contenu de la liste retournée
        // Vérifie les interactions avec les mocks
        verify(adresseRepository).findAll(); // Vérifie que la méthode findAll() du repository est appelée (liste d'elements)
        verify(adresseMapperImpl).toDto(adresseEntity); // Vérifie que la méthode toDto() du mapper est appelée
        // Logging
        log.info(adressesDTO.toString());
    }

    //Test JUnit Mockito pour la méthode getAllAdresses (negative scenarios)
    @Test
    @DisplayName("Test JUnit Mockito pour la méthode getAllAdresses (negative scenarios)")
    public void givenEmptyAdresseList_whenGetAllAdresses_thenReturnEmptyAdressesList() {
        // given - préconditions ou configuration
        adresseEntity = new ProduitEntity();
        adresseEntity.setId(2L);
        adresseEntity.setRue("jura");
        adresseEntity.setEmail("totor@gmail.com");
        adresseEntity.setVille("Noisy-lee-Grrand");
        adresseEntity.setPays("Frarnce");
        adresseEntity.setCodePostal("8893130");
        adresseEntity.setCommune("LeGrrand");
        adresseEntity.setRegion("Parris");
        adresseEntity.setTelephone("788888888888");
        // Mock du comportement
        given(adresseRepository.findAll()).willReturn(Collections.emptyList());
        given(adresseMapperImpl.toDto(adresseEntity)).willReturn(expectedSavedAdresseDTO);
        // when - exécute l'opération réelle
        List<ProduitDTO> adressesDTO = produitServiceImpl.getAllAdresses();
        // then - vérifie la sortie
        assertThat(adressesDTO).isEmpty(); // Vérifie que la liste retournée est vide
        assertThat(adressesDTO).hasSize(0); // Vérifie la taille de la liste retournée (tableau vide )
        // Vérifie les interactions avec les mocks
        verify(adresseRepository).findAll(); // Vérifie que la méthode findAll() du repository est appelée
        verify(adresseMapperImpl, never()).toDto(any()); // Vérifie que la méthode toDto() du mapper n'est jamais appelée car la liste est vide
        // Logging
        log.info(adressesDTO.toString());
    }

    // JUnit test for getAdressesById() method
    @Test
    @DisplayName("JUnit test for getAdressesById() method")
    public void givenAdresseId_whenGetAdresseById_thenReturnAdresseObject() {
        // given - precondition or setup
        given(adresseRepository.findById(2L)).willReturn(Optional.of(adresseEntity));
        given(adresseMapperImpl.toDto(adresseEntity)).willReturn(expectedSavedAdresseDTO);
        // when -  action or the behaviour that we are g oing test
        ProduitDTO adresseDTO = produitServiceImpl.getAdresseById(2L).orElseThrow();
        // then - verify the output
        assertThat(adresseDTO).isNotNull(); // Vérifie que l'objet retourné n'est pas null
        assertThat(adresseDTO).usingRecursiveComparison().isEqualTo(expectedSavedAdresseDTO); // Vérifie le contenu de l'objet retourné
        // Vérifie les interactions avec les mocks
        verify(adresseRepository).findById(2L); // Vérifie que la méthode findById() du repository est appelée
        verify(adresseMapperImpl).toDto(adresseEntity); // Vérifie que la méthode toDto() du mapper est appelée
        // Logging
        log.info(adresseDTO.toString());
    }

    // JUnit test for updating the adresse method
    @Test
    @DisplayName("JUnit test for updating the address method")
    public void givenAddressObject_whenUpdateAddress_thenReturnUpdatedAddress() {
        // given - precondition or setup
        ProduitDTO existingAdresseDTO = ProduitDTO.builder().id(2L).rue("jura").ville("Noisy-le-Grand").codePostal("93130").pays("France").region("Paris").commune("LeGrand").telephone("23886898985").email("toto@gmail.com").build();
        ProduitEntity existingAdresseEntity = new ProduitEntity(); // Initialiser l'entité adresse avec des données fictive ou réelles
        given(adresseRepository.findById(existingAdresseDTO.getId())).willReturn(Optional.of(existingAdresseEntity));
        given(adresseRepository.save(any(ProduitEntity.class))).willReturn(existingAdresseEntity);
        given(adresseMapperImpl.toEntity(any(ProduitDTO.class))).willReturn(existingAdresseEntity);
        given(adresseMapperImpl.toDto(existingAdresseEntity)).willReturn(existingAdresseDTO);
        // when - action or the behavior that we are going to test
        ProduitDTO updatedAdresse = produitServiceImpl.updateAdresse(existingAdresseDTO);
        // then - assert the result
        assertThat(updatedAdresse).isNotNull();
        assertThat(updatedAdresse.getId()).isEqualTo(2L); // L'identifiant de l'adresse à mettre à jour
        assertThat(updatedAdresse.getRue()).isEqualTo("jura");
        assertThat(updatedAdresse.getVille()).isEqualTo("Noisy-le-Grand");
        assertThat(updatedAdresse.getCodePostal()).isEqualTo("93130");
        assertThat(updatedAdresse.getPays()).isEqualTo("France");
        assertThat(updatedAdresse.getRegion()).isEqualTo("Paris");
        assertThat(updatedAdresse.getCommune()).isEqualTo("LeGrand");
        assertThat(updatedAdresse.getTelephone()).isEqualTo("23886898985");
        assertThat(updatedAdresse.getEmail()).isEqualTo("toto@gmail.com");
        // Logging
        log.info("Updated address: " + updatedAdresse.toString());
    }

    // JUnit test for deleting address by id
    @Test
    @DisplayName("JUnit test for deleting address by id")
    public void givenAdresseId_whenDeleteAdresse_thenNothing() {
        // given - precondition or setup
        long adresseId = 2L;
        // Mock du comportement du repository pour renvoyer true lors de la vérification de l'existence de l'adresse par son identifiant
        given(adresseRepository.existsById(adresseId)).willReturn(true);
        // when - action or the behaviour that we are going test
        produitServiceImpl.deleteAdresse(adresseId);
        // then - assert the result
        verify(adresseRepository).deleteById(adresseId); // Vérifie que la méthode deleteById() du repository est appelée
        // Logging
        log.info("Deleted");
    }


}
*/
