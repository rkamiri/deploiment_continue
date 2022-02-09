package com.example.cc1romainkamiri.kernel;

public interface EventDispatcher<E extends Event> {
    void dispatch(E event);
}
