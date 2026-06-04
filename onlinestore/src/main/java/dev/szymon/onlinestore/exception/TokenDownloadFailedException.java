package dev.szymon.onlinestore.exception;

public class TokenDownloadFailedException extends RuntimeException {
    public TokenDownloadFailedException(String message) {
        super(message);
    }
}
