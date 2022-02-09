package com.example.cc1romainkamiri.kernel;

public interface QueryBus {
    <Q extends Query, R> R send(Q query);
}
