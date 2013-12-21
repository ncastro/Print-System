package exceptions;

public class PhotoOverSizeException extends RuntimeException {
	
	static final long serialVersionUID = 0L;


	public PhotoOverSizeException() {
		super();
	}

	public PhotoOverSizeException( String message ) {
		super(message);
	}


}
