package exceptions;

public class PrinterNeedMaintenanceException extends RuntimeException {
	
	static final long serialVersionUID = 0L;


	public PrinterNeedMaintenanceException() {
		super();
	}

	public PrinterNeedMaintenanceException( String message ) {
		super(message);
	}

}
