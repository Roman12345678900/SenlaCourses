package senla.com.exception;

public class EquipmentMaintenanceNotFoundException extends RuntimeException{

    public EquipmentMaintenanceNotFoundException(Long id){
        super(String.format("Equipment maintenance id %d not found", id));
    }
}
