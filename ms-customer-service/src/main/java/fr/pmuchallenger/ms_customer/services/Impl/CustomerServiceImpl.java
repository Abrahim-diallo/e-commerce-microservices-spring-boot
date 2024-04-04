package fr.pmuchallenger.ms_customer.services.Impl;

import fr.pmuchallenger.ms_customer.exceptions.CustomerNotFoundException;
import fr.pmuchallenger.ms_customer.mappers.CustomerMapper;
import fr.pmuchallenger.ms_customer.model.dto.request.AddCustomerRequest;
import fr.pmuchallenger.ms_customer.model.dto.response.CustomerDto;
import fr.pmuchallenger.ms_customer.model.entities.Customer;
import fr.pmuchallenger.ms_customer.repository.CustomerRepository;
import fr.pmuchallenger.ms_customer.services.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto addCustomer(AddCustomerRequest addCustomerRequest) {
        log.debug("Adding customer: {}", addCustomerRequest);
        // Vérification si la requête est nulle
        if (addCustomerRequest == null) {
            throw new CustomerNotFoundException("AddCustomerRequest cannot be null");
        }
        return CustomerMapper.toDto(customerRepository.save(CustomerMapper.toEntity(addCustomerRequest)));
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        log.info("Getting customer with id: {}", id);
        log.debug("Getting customer with id: {}", id);
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(CustomerMapper::toDto).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public CustomerDto updateCustomer(Long id, AddCustomerRequest addCustomerRequest) {
        log.info("Updating customer with id: {}", id);
        log.debug("Updating customer with id: {}", id);
        // Validation des entrées
        if (addCustomerRequest == null) {
            throw new IllegalArgumentException("AddCustomerRequest cannot be null");
        }
        // Recherche du client dans la base de données
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        // Mise à jour avec les données fournies
        customer.setFullName(addCustomerRequest.getFullName());
        customer.setEmail(addCustomerRequest.getEmail());
        customer.setPassword(addCustomerRequest.getPassword());
        customer.setPhone(addCustomerRequest.getPhone());
        customer.setAddresses(addCustomerRequest.getAddresses());
        customer.setCardNumbers(addCustomerRequest.getCardNumbers());
        // Enregistrement des modifications dans la base de données
        Customer updatedCustomer = customerRepository.save(customer);
        // Retour du DTO correspondant mis à jour
        return CustomerMapper.toDto(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        log.info("Deleting customer with id " + id);
        log.debug("Deleting customer with id " + id);
        // Vérification de l'existence du client avant la suppression
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        }
        // Suppression
        customerRepository.deleteById(id);
    }


}
