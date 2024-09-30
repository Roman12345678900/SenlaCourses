package senla.com.exception;

public class ProfilesNotFoundException extends RuntimeException{

    public ProfilesNotFoundException(Long id) {
        super(String.format("Profile id %s not found", id));
    }
}
