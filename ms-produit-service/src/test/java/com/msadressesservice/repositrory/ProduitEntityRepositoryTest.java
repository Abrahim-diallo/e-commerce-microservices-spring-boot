/*
package com.msadressesservice.repositrory;

import com.msadressesservice.entities.ProduitEntity;
import com.msadressesservice.repositories.ProduitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AdresseMapperImpl.class)
public class ProduitEntityRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(ProduitEntityRepositoryTest.class);

    @Autowired
    private ProduitRepository adresseRepository;

    @Autowired
    private AdresseMapperImpl adresseMapperImpl;

    @Test
    @DisplayName("[Le test enregistre une ligne dans la base de données et utilise un DTO pour la validation]")
    public void givenAdresseObject_whenSave_thenReturnSavedAdresseDTO() {
        // given
        ProduitEntity adresseEntity = ProduitEntity.builder()
                .rue("jurandom")
                .email("lodiaakhalil@gmail.com")
                .ville("Noisy-le-Grand")
                .pays("France")
                .codePostal("93130")
                .commune("noisy")
                .region("Paris")
                .telephone("068585955")
                .build();
        // when
        ProduitEntity savedEntity = adresseRepository.save(adresseEntity);
        ProduitDTO savedDTO = adresseMapperImpl.toDto(savedEntity);
        // then
        assertThat(savedDTO).isNotNull();
        assertThat(savedDTO.getEmail()).isEqualTo(adresseEntity.getEmail());
    }

    @Test
    @DisplayName("Le test vérifie la récupération de la liste des adresses et utilise des DTOs pour la validation")
    public void givenAdresseList_whenFindAll_thenAdresseListAsDTOs() {
        // Given
        ProduitEntity adresse1 = ProduitEntity.builder()
                .rue("jurandom")
                .email("lodiaakhalil@gmail.com")
                .ville("Noisy-le-Grand")
                .pays("France")
                .codePostal("93130")
                .commune("noisy")
                .region("Paris")
                .telephone("0888888099")
                .build();
        ProduitEntity adresse2 = ProduitEntity.builder()
                .rue("jura")
                .email("lodiaakhalil1@domain.com")
                .ville("Bobigny")
                .pays("France")
                .codePostal("93000")
                .commune("bobigny")
                .region("Paris")
                .telephone("0123456789")
                .build();
        adresseRepository.save(adresse1);
        adresseRepository.save(adresse2);
        // When
        List<ProduitDTO> adresseDTOList = adresseRepository.findAll()
                .stream().map(adresseMapperImpl::toDto)
                .collect(Collectors.toList());
        // Then
        assertThat(adresseDTOList).isNotEmpty();
        assertThat(adresseDTOList.size()).isGreaterThanOrEqualTo(2);
        // Logging for demonstration purposes
        adresseDTOList.forEach(adresseDTO -> logger.info(adresseDTO.toString()));
    }

    @Test
    @DisplayName("JUnit test for get adresse by email address using DTO for validation")
    public void givenAdresseEmail_whenFindByEmail_thenReturnAdresseDto() {
        // given - precondition or setup
        ProduitEntity adresseEntity = ProduitEntity.builder()
                .rue("jurandom")
                .email("lodiaakhalil11@gmail.com")
                .ville("Noisy-le-Grand")
                .pays("France")
                .codePostal("93130")
                .commune("Le Grand")
                .region("Paris")
                .telephone("2356898985")
                .build();
        adresseRepository.save(adresseEntity);
        // when - action or the behaviour that we are going test
        Optional<ProduitEntity> foundEntityOptional = adresseRepository.findByEmail(adresseEntity.getEmail());
        ProduitDTO foundDto = foundEntityOptional.map(adresseMapperImpl::toDto).orElse(null);
        // then - verify the output
        assertThat(foundDto).isNotNull();
        assertThat(foundDto.getEmail()).isEqualTo(adresseEntity.getEmail());
    }

    // JUnit test for updating the adresse repository
    @Test
    @DisplayName("JUnit test for updating the adresse repository")
    public void givenAdresseObject_whenUpdateAdresse_thenReturnUpdatedAdresse() {
        // given - precondition or setup
        ProduitEntity adresseEntity = ProduitEntity.builder()
                .rue("jurandom")
                .email("lodiaakhalil11@gmail.com")
                .ville("Noisy-le-Grand")
                .pays("France")
                .codePostal("93130")
                .commune("Le Grand")
                .region("Paris")
                .telephone("2356898985")
                .build();
        adresseRepository.save(adresseEntity);
        // when -  action or the behaviour that we are going test
        ProduitEntity updatedEntity = adresseRepository.findById(adresseEntity.getId())
              .map(adresse -> {
                    adresse.setRue("jura");
                    adresse.setEmail("khalid@gmail.com");
                    adresse.setVille("Bobigny");
                    adresse.setPays("France");
                    adresse.setCodePostal("93000");
                    adresse.setCommune("bobigny");
                    adresse.setRegion("Paris");
                    adresse.setTelephone("0123456789");
                    return adresse;
                }).orElse(null);
        adresseRepository.save(updatedEntity);

        // then - verify the output
        assertThat(updatedEntity).isNotNull();
        assertThat(updatedEntity.getRue()).isEqualTo("jura");
        assertThat(updatedEntity.getEmail()).isEqualTo("khalid@gmail.com");
        assertThat(updatedEntity.getVille()).isEqualTo("Bobigny");
        assertThat(updatedEntity.getPays()).isEqualTo("France");
        assertThat(updatedEntity.getCodePostal()).isEqualTo("93000");
        assertThat(updatedEntity.getCommune()).isEqualTo("bobigny");
        assertThat(updatedEntity.getRegion()).isEqualTo("Paris");
        assertThat(updatedEntity.getTelephone()).isEqualTo("0123456789");
        // Logging for demonstration purposes
        logger.info(updatedEntity.toString());
    }

    // JUnit test for deleting an edresse entity
    @Test
    @DisplayName("JUnit test for deleting an edresse entity")
    public void givenAdreesseObject_whenDelete_thenRremoveAdresse() {
        // given - precondition or setup
        //---deleting an edresse entity by id---
        ProduitEntity adresseEntity = ProduitEntity.builder()
               .rue("jurandom")
               .email("lodiaakhalil11@gmail.com")
               .ville("Noisy-le-Grand")
               .pays("France")
               .codePostal("93130")
               .commune("Le Grand")
               .region("Paris")
               .telephone("2356898985")
               .build();
        adresseRepository.save(adresseEntity);
        // when -  action or the behaviour that we are going test
        //---deleting an edresse entity by id---
        adresseRepository.deleteById(adresseEntity.getId());
        Optional<ProduitEntity> adresseEntityOptional = adresseRepository.findById(adresseEntity.getId());
        // then - verify the output
        assertThat(adresseEntityOptional).isEmpty();
        // Logging for demonstration purposes
        logger.info(adresseEntityOptional.toString());
    }

    // JUnit test for testing for custtom query using JPQL with index
    @Test
    @DisplayName("JUnit test for testing for custtom query using JPQL with index")
    public void givenRueAndEmail_whenFindByJPQL_thenReturnAdresseObject() {
        // given - precondition or setup
        ProduitEntity adresseEntity = ProduitEntity.builder()
                .rue("jurandom")
                .email("lodiaakhalil11@gmail.com")
                .ville("Noisy-le-Grand")
                .pays("France")
                .codePostal("93130")
                .commune("Le Grand")
                .region("Paris")
                .telephone("2356898985")
                .build();
        adresseRepository.save(adresseEntity);
        String rue = "jurandom";
        String email = "lodiaakhalil11@gmail.com";
        // when -  action or the behaviour that we are going test
        ProduitEntity adresseEntityJPQL = adresseRepository.findByJPQL(rue, email);
        ProduitDTO adresseDTO = adresseMapperImpl.toDto(adresseEntityJPQL);
        // then - verify the output
        assertThat(adresseDTO).isNotNull(); // Vérifie si l'objet retourné n'est pas null
        assertThat(adresseDTO.getEmail()).isEqualTo(email); // Vérifie si l'email de l'objet retourné correspond à celui attendu
        // Logging for demonstration purposes
        logger.info(adresseDTO.toString());
    }

    // JUnit test for define custom query using JPDL whith nmed param
    @Test
    @DisplayName("JUnit test for define custom query using JPDL whith nmed param")
    public void givenRueAndEmail_whenFindByJPQLNamedParams_thenReturnAdresseObject() {
        // given - precondition or setup
        ProduitEntity adresseEntity = ProduitEntity.builder()
                .rue("jurandom")
                .email("lodiaakhalil11@gmail.com")
                .ville("Noisy-le-Grand")
                .pays("France")
                .codePostal("93130")
                .commune("Le Grand")
                .region("Paris")
                .telephone("2356898985")
                .build();
        adresseRepository.save(adresseEntity);
        String rue = "jurandom";
        String email = "lodiaakhalil11@gmail.com";
        // when -  action or the behaviour that we are going test
        ProduitEntity adresseEntityJPQLNamedParams = adresseRepository.findByJPQLNamedParams(rue, email);
        ProduitDTO adresseDTO = adresseMapperImpl.toDto(adresseEntityJPQLNamedParams);
        // then - verify the output
        assertThat(adresseDTO).isNotNull(); // Vérifie si l'objet retourné n'est pas null
        assertThat(adresseDTO.getEmail()).isEqualTo(email); // Vérifie si l'email de l'objet retourné correspond à celui attendu
        // Logging for demonstration purposes
        logger.info(adresseDTO.toString());
    }

    // JUnit test custom query using native SQL query index params
    @Test
    @DisplayName("Unit test custom query using native SQL query index params")
    public void givenRueAndEmail_whenFindByNativeSQL_thenReturnAdresse() {
        // given - precondition or setup
        ProduitEntity adresseEntityNatSQL = ProduitEntity.builder()
                .rue("jurandom")
                .email("lodiaakhalil11@gmail.com")
                .ville("Noisy-le-Grand")
                .pays("France")
                .codePostal("93130")
                .commune("Le Grand")
                .region("Paris")
                .telephone("2356898985")
                .build();
        adresseRepository.save(adresseEntityNatSQL);
        // when -  action or the behaviour that we are going test
        ProduitEntity adresseEntityNativeSQL = adresseRepository.findByNativeSQL(adresseEntityNatSQL.getRue(), adresseEntityNatSQL.getEmail());
        ProduitDTO adresseDTO = adresseMapperImpl.toDto(adresseEntityNativeSQL);
        // then - verify the output
        assertThat(adresseDTO).isNotNull(); // Vérifie si l'objet retourné n'est pas null
        assertThat(adresseDTO.getEmail()).isEqualTo(adresseEntityNatSQL.getEmail()); // Vérifie si l'email de l'objet retourné correspond à celui attendu
        // Logging for demonstration purposes
        logger.info(adresseDTO.toString());
    }

   // JUnit test custom query using native SQL query with named params
    @Test
    @DisplayName("Unit test custom query using native SQL query  with Named params")
    public void givenRueAndEmail_whenFindByNativeSQLNamedParams_thenReturnAdresse() {
        // given - precondition or setup
        ProduitEntity adresseEntityNatSQL = ProduitEntity.builder()
                .rue("jurandom")
                .email("lodiaakhalil11@gmail.com")
                .ville("Noisy-le-Grand")
                .pays("France")
                .codePostal("93130")
                .commune("Le Grand")
                .region("Paris")
                .telephone("2356898985")
                .build();
        adresseRepository.save(adresseEntityNatSQL);
        // when -  action or the behaviour that we are going test
        ProduitEntity adresseEntityNativeSQL = adresseRepository.findByNativeSQLNamedParams(adresseEntityNatSQL.getRue(), adresseEntityNatSQL.getEmail());
        ProduitDTO adresseDTO = adresseMapperImpl.toDto(adresseEntityNativeSQL);
        // then - verify the output
        assertThat(adresseDTO).isNotNull(); // Vérifie si l'objet retourné n'est pas null
        assertThat(adresseDTO.getEmail()).isEqualTo(adresseEntityNatSQL.getEmail()); // Vérifie si l'email de l'objet retourné correspond à celui attendu
        // Logging for demonstration purposes
        logger.info(adresseDTO.toString());
    }

    @TestConfiguration
    public static class MapperConfig {
        @Bean
        public AdresseMapperImpl adresseMapperImpl() {
            return new AdresseMapperImpl();
        }
    }


}
*/
