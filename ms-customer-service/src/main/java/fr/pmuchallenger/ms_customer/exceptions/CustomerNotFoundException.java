package fr.pmuchallenger.ms_customer.exceptions;

/*
 * @author: Ibrahima DIALLO
 */
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String format) {
        super("Customer not found!");
    }
}