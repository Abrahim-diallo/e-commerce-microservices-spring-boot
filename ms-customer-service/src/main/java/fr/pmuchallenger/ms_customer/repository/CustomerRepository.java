package fr.pmuchallenger.ms_customer.repository;

import fr.pmuchallenger.ms_customer.model.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * @author: Ibrahima DIALLO
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

