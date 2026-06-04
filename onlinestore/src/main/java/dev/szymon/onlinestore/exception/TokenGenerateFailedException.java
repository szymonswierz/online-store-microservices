package dev.szymon.onlinestore.exception;

public class TokenGenerateFailedException extends RuntimeException {
    public TokenGenerateFailedException(String message) {
        super(message);
    }
}
