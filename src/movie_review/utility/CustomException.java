package movie_review.utility;

public class CustomException extends Exception{
    private static final long serialVersionUID = 1L;

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }
    
}
