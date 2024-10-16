package rentalsystem.exceptions;

/**
 * Exception for empty stations
 */
public class NoVehiclesAvailableException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the exception
	 * @param msg The message with which the exception is raised
	 */
	public NoVehiclesAvailableException(String msg) {
		super(msg);
	}
}