package main.db.data.structures.position;

/**
 * An exception that will be thrown if there is an error when casting the exception
 *
 */
public class PositionException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
    public PositionException() {
        super();
    }
    
    public PositionException(String message) {
        super(message);
    }
    
    public PositionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public PositionException(Throwable cause) {
        super(cause);
    } 
	
}
