package senla.com.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id) {
        super(String.format("User id %s not found!", id));
    }
}
