package com.msproduit.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProduitAPIException extends RuntimeException {

    private static final long serialVersionUID = -6593330219878485669L;

    private final String status;
    private final String message;

    public ProduitAPIException(String status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public ProduitAPIException(String status, String message, Throwable exception) {
        super(exception);
        this.status = status;
        this.message = message;
    }
}
