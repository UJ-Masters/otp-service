package com.masters.uj.otp.exception;

public class OtpExistsException extends RuntimeException{
    public OtpExistsException(String message) {
        super(message);
    }
}
