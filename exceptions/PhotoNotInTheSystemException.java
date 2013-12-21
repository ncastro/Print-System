package exceptions;

public class PhotoNotInTheSystemException extends RuntimeException {

	static final long serialVersionUID = 0L;


	public PhotoNotInTheSystemException() {
		super();
	}

	public PhotoNotInTheSystemException( String message )
	{
		super(message);
	}

}
