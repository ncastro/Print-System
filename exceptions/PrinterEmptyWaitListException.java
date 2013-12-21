package exceptions;

public class PrinterEmptyWaitListException extends RuntimeException {
	
	static final long serialVersionUID = 0L;


	public PrinterEmptyWaitListException() {
		super();
	}

	public PrinterEmptyWaitListException( String message ) {
		super(message);
	}

}
