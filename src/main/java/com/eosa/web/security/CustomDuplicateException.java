package com.eosa.web.security;

public class CustomDuplicateException extends RuntimeException {
    
    public CustomDuplicateException() {
        super();
    }

    public CustomDuplicateException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CustomDuplicateException(String msg) {
        super(msg);
    }

    public CustomDuplicateException(Throwable cause) {
        super(cause);
    }

}
