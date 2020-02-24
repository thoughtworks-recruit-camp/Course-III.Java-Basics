public class ValueReadException extends RuntimeException {
    private final String message;
    private Throwable cause;

    public ValueReadException(String message) {
        this.message = message;
    }

    public ValueReadException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }
}
