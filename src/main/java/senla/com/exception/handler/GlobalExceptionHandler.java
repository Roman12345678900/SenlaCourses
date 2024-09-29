package senla.com.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import senla.com.exception.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            UserNotFoundException.class,
            TrainerSchedulesNotFoundException.class,
            SeasonTicketsNotFoundException.class,
            SeasonTicketsTypeNotFoundException.class,
            SchedulesNotFoundException.class,
            RoleNotFoundException.class,
            ReviewsNotFoundException.class,
            ProfilesNotFoundException.class,
            PaymentsNotFoundException.class,
            EquipmentNotFoundException.class,
            EquipmentMaintenanceNotFoundException.class,
            ClassesNotFoundException.class,
            CardInfoNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFoundException(Exception e) {
        return new ResponseEntity<>("Not found error:" + e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
