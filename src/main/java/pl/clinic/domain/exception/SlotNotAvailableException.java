package pl.clinic.domain.exception;

public class SlotNotAvailableException extends RuntimeException {
    public SlotNotAvailableException(final String message) {
        super(message);
    }
}
