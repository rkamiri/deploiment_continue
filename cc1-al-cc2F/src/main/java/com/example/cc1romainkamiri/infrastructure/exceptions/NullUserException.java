package com.example.cc1romainkamiri.infrastructure.exceptions;

public class NullUserException extends Exception{
    public NullUserException(){
        super("User must not be null");
    }
}
