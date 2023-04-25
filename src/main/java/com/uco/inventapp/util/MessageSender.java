package com.uco.inventapp.util;

public interface MessageSender<T> {
    void execute(T message, String idMessage);
}