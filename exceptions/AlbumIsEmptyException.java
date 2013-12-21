package exceptions;

public class AlbumIsEmptyException extends RuntimeException{
	
	static final long serialVersionUID = 0L;


	public AlbumIsEmptyException() {
		super();
	}

	public AlbumIsEmptyException( String message ) {
		super(message);
	}

}
