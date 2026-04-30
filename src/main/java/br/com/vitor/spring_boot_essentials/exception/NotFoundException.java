package br.com.vitor.spring_boot_essentials.exception;

public class NotFoundException extends Exception{

    public NotFoundException(String message) {
        super(message);
    }
}