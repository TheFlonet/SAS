package businesslogic;

import java.time.DateTimeException;

public class PastShiftException extends DateTimeException {
    public PastShiftException(String message) {
        super(message);
    }

    public PastShiftException(String message, Throwable cause) {
        super(message, cause);
    }
}
