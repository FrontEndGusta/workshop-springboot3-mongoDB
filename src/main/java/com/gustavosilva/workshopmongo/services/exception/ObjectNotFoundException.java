package com.gustavosilva.workshopmongo.services.exception;

import java.io.Serial;

//RuntimeException o compilador não obriga a tratar
public class ObjectNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String msg) {
        super(msg);
    }
}
