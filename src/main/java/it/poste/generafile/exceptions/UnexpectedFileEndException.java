package it.poste.generafile.exceptions;

public class UnexpectedFileEndException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3692044966057879397L;

	public UnexpectedFileEndException() {
		// TODO Auto-generated constructor stub
	}

	public UnexpectedFileEndException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UnexpectedFileEndException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public UnexpectedFileEndException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UnexpectedFileEndException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
