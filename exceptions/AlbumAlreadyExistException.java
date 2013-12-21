package exceptions;

public class AlbumAlreadyExistException extends RuntimeException {

	static final long serialVersionUID = 0L;


	public AlbumAlreadyExistException() {
		super();
	}

	public AlbumAlreadyExistException( String message ) {
		super(message);
	}

}
