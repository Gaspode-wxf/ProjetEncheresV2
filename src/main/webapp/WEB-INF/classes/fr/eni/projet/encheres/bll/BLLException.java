package fr.eni.projet.encheres.bll;

/**
 * @author William "Gaspode" Freyer
 *
 */
public class BLLException extends Exception {

	private static final long serialVersionUID = 1L;

	public BLLException() {
	}

	public BLLException(String message) {
		super(message);
	}

	public BLLException(Throwable cause) {
		super(cause);
	}

	public BLLException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getMessage() {
		return this.getLocalizedMessage();
	}

}
