package senla.com.exception;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException(Long id) {
        super(String.format("Role id %d not found", id));
    }
}
