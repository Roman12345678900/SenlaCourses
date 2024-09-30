package senla.com.exception;

public class SeasonTicketsNotFoundException extends RuntimeException{

    public SeasonTicketsNotFoundException(Long id) {
        super(String.format("Season tickets id %s not found", id));
    }
}
