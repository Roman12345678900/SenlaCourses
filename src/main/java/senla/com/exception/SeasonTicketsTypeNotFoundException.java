package senla.com.exception;

public class SeasonTicketsTypeNotFoundException extends RuntimeException {

    public SeasonTicketsTypeNotFoundException(Long id) {
        super(String.format("Season tickets type id %s not found", id));
    }
}
