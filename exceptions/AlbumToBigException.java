package exceptions;

public class AlbumToBigException extends RuntimeException {
	
	static final long serialVersionUID = 0L;


	public AlbumToBigException() {
		super();
	}

	public AlbumToBigException( String message ) {
		super(message);
	}

}
