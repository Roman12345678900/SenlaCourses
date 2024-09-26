package senla.com.exception;

public class PaymentsNotFoundException extends RuntimeException{

    public PaymentsNotFoundException(Long id) {
        super(String.format("Payments id %s not found", id));
    }
}
