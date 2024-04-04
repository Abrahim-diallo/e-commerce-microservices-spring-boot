package fr.mspaiement.ms_paiement.exceptions;

public class PaiementNotFoundException extends RuntimeException{
    public PaiementNotFoundException(String s) {
        super("Payment not found");
    }
}
