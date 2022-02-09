package com.example.cc1romainkamiri.infrastructure.exceptions;

public class BadPaymentException extends Exception{
    public BadPaymentException(){
        super("payment did not complete");
    }
}
