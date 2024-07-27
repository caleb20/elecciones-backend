package com.caleb.elecciones.exception;

public class VotoExistenteException extends RuntimeException {
    public VotoExistenteException(String message) {
        super(message);
    }
}
