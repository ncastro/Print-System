package exceptions;

public class PrinterNotInTheSystemException extends RuntimeException {
	
	static final long serialVersionUID = 0L;


	public PrinterNotInTheSystemException() {
		super();
	}

	public PrinterNotInTheSystemException( String message ) {
		super(message);
	}

}
