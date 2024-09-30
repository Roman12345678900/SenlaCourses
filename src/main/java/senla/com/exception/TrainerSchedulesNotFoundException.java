package senla.com.exception;

public class TrainerSchedulesNotFoundException extends RuntimeException {

    public TrainerSchedulesNotFoundException(Long id) {
        super(String.format("Trainer schedules id %s not found!",id));
    }
}
