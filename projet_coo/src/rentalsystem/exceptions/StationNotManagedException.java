package rentalsystem.exceptions;

/**
 * Exception for stations that are not managed by the control center
 */
public class StationNotManagedException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the exception
	 * @param m The message with which the exception is raised
	 */
	public StationNotManagedException(String m) {
		super(m);
	}
}
