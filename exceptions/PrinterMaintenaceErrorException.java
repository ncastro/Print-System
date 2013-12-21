package exceptions;

public class PrinterMaintenaceErrorException extends RuntimeException {
	
	static final long serialVersionUID = 0L;


	public PrinterMaintenaceErrorException() {
		super();
	}

	public PrinterMaintenaceErrorException( String message ) {
		super(message);
	}

}
