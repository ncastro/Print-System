package exceptions;

public class PhotoAlreadyExistException extends RuntimeException {
	
	 static final long serialVersionUID = 0L;


	    public PhotoAlreadyExistException() {
	        super();
	    }

	    public PhotoAlreadyExistException( String message )
	    {
	        super(message);
	    }

}
