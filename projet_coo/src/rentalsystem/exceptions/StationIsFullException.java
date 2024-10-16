package rentalsystem.exceptions;

/**
 * Exception for full stations
 */
public class StationIsFullException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the exception
	 * @param msg The message with which the exception is raised
	 */
	public StationIsFullException(String msg) {
		super(msg);
	}
}
