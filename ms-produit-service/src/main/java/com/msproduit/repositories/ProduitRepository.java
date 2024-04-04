package com.msproduit.repositories;

import com.msproduit.entities.ProduitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<ProduitEntity,Long> {
    boolean existsBySku(String sku);
 /*   Optional<ProduitEntity> findByEmail(String email);

    // define custom query using JPDL whith index params
    @Query("select  e from ProduitEntity e where e.rue =  ?1 and e.email = ?2 ")
    ProduitEntity findByJPQL(String rue, String email);

    // define custom query using JPDL whith nmed params
    @Query("select  e from ProduitEntity e where e.rue =:rue and e.email = :email ")
    ProduitEntity findByJPQLNamedParams(@Param("rue") String rue, @Param("email") String email);

    // define custom query using native SQL query index params
    @Query(value = "select * from adresses e where e.rue = ?1 and e.email =?2 ", nativeQuery = true)
    ProduitEntity findByNativeSQL(String rue, String email);

    // define custom query using native SQL query named params
    @Query(value = "select * from adresses e where e.rue =:rue and e.email =:email ", nativeQuery = true)
    ProduitEntity findByNativeSQLNamedParams(@Param("rue") String rue, @Param("email") String email);*/
}
