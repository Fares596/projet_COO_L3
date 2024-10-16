package rentalsystem.rearrangements;
import java.util.List;

import rentalsystem.Station;
import rentalsystem.exceptions.*;
import rentalsystem.Vehicle;

/**
 * Interface for different rearrangement methods
 * @author nicolas.han.etu
 *
 */
public interface RearrangementMethod<T extends Vehicle> {
	
	/**
	 * Rearranges the Vehicles in the stations
	 * @param stations The list of stations to rearrange
	 * @throws VehicleNotInStationException Should not happen, cf ControlCenter's rearrange method
	 */
	public void rearrange(List<Station<T>> stations) throws VehicleNotInStationException;
}
