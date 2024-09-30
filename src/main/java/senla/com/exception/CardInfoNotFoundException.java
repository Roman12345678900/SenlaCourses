package senla.com.exception;

public class CardInfoNotFoundException extends RuntimeException{

    public CardInfoNotFoundException(Long cardNumber) {
        super(String.format("Card number %s not found", cardNumber));
    }
}
