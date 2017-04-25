package db.data.structures.position;

/**
 * An exception that will be thrown if there is an error with the db.data.structures.position.position List
 *
 */
public class PositionListException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
    public PositionListException() {
        super();
    }
    
    public PositionListException(String message) {
        super(message);
    }
    
    public PositionListException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public PositionListException(Throwable cause) {
        super(cause);
    } 
	
}
