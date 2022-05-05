package at.home.garageremote.exception;

public class MissingKeyException extends RuntimeException {
    public MissingKeyException(String message) {
        super(message);
    }
}
