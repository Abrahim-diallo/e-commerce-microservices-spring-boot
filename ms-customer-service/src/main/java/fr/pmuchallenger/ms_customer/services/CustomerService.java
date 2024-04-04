package fr.pmuchallenger.ms_customer.services;

import fr.pmuchallenger.ms_customer.model.dto.request.AddCustomerRequest;
import fr.pmuchallenger.ms_customer.model.dto.response.CustomerDto;

/**
 * @author: Ibrahima DIALLO
 */
public interface CustomerService {
    CustomerDto addCustomer(AddCustomerRequest addCustomerRequest);
     CustomerDto getCustomerById(Long id);

     CustomerDto updateCustomer(Long id, AddCustomerRequest addCustomerRequest);

     void deleteCustomer(Long id);

}
