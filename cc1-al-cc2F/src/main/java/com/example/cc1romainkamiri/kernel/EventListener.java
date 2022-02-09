package com.example.cc1romainkamiri.kernel;

public interface EventListener<E extends Event> {
    void listenTo(E event);
}
