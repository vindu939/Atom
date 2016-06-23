package com.atom.auth;

/**
 * Created by aravindp on 21/6/16.
 */
public class AuthException extends RuntimeException{

    public AuthException(String message, Throwable cause) {
            super(message, cause);
        }

    public AuthException(String message) {
            super(message);
        }

}
