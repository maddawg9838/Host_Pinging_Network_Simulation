// Creates own exception for the program by
// extending an unchecked exception class
public class EventException extends RuntimeException {

    public EventException(String message) {
        super(message);
    }
}