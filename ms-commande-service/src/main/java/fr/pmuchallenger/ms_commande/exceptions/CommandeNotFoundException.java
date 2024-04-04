package fr.pmuchallenger.ms_commande.exceptions;

/*
 * @author: Ibrahima DIALLO
 */
public class CommandeNotFoundException extends RuntimeException {
    public CommandeNotFoundException(String format) {
        super("Product not found!");
    }
}