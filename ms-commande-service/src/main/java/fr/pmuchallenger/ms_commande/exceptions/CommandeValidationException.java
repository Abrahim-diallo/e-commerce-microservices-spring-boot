package fr.pmuchallenger.ms_commande.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CommandeValidationException extends RuntimeException {
    public CommandeValidationException(String message) {
        super(message);
    }
}
