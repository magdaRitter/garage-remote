package at.home.garageremote.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MissingKeyException extends RuntimeException {
    public MissingKeyException(String message) {
        super(message);
        log.error(message);
    }
}
