package rentalsystem.exceptions;

/**
 * Exception for vehicles that are not in the station but are still being rented
 */
public class VehicleNotInStationException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the exception
	 * @param m The message with which the exception is raised
	 */
	public VehicleNotInStationException(String m) {
		super(m);
	}
}
