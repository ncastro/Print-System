package exceptions;

public class PrinterAlreadyExistException extends RuntimeException {


	static final long serialVersionUID = 0L;


	public PrinterAlreadyExistException() {
		super();
	}

	public PrinterAlreadyExistException( String message ) {
		super(message);
	}

}
