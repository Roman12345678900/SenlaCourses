package senla.com.exception;

public class ReviewsNotFoundException extends RuntimeException{

    public ReviewsNotFoundException(Long id) {
        super(String.format("Reviews id %s not found", id));
    }
}
