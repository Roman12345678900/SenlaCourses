package senla.com.exception;

public class ClassesNotFoundException extends RuntimeException{

    public ClassesNotFoundException(Long id) {
        super(String.format("Class id %s not found", id));
    }
}
