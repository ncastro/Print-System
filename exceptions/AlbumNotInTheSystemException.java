package exceptions;

public class AlbumNotInTheSystemException extends RuntimeException {
	
	static final long serialVersionUID = 0L;


	public AlbumNotInTheSystemException() {
		super();
	}

	public AlbumNotInTheSystemException( String message )
	{
		super(message);
	}

}
