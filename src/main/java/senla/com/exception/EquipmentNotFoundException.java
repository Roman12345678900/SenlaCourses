package senla.com.exception;

public class EquipmentNotFoundException extends RuntimeException{

    public EquipmentNotFoundException(Long id) {
        super(String.format("Equipment id %s not found", id));
    }
}
