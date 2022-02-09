package com.example.cc1romainkamiri.kernel;

public interface CommandHandler<C extends Command, R> {
    R handle(C command);
}
