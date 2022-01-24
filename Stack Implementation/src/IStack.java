package com.company;

import java.util.EmptyStackException;

public interface IStack <T> {

    public void push(T value);
    public T pop() throws EmptyStackException;
    public T top() throws EmptyStackException;
    public int size();
    public boolean isEmpty();
    public boolean isFull();
}
