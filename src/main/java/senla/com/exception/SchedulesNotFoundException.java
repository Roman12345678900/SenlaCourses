package senla.com.exception;

public class SchedulesNotFoundException extends RuntimeException{

    public SchedulesNotFoundException(Long id) {
        super(String.format("Schedule id %s not found: ", id));
    }
}
