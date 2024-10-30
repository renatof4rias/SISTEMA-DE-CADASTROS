package com.softelse.exeption;

public class NomeException extends RuntimeException{
    public NomeException() {
        System.out.println("Nome de Usuário deve ter no mínimo 10 caracteres. ");
    }

    public NomeException(String message) {
        super(message);
    }
}
