package it.poste.generafile.exceptions;

public class FileReadErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5437557124202255750L;

	public FileReadErrorException(String string) {
		super(string);
	}
}
