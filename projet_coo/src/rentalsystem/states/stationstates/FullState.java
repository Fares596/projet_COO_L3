package rentalsystem.states.stationstates;
import rentalsystem.*;
import rentalsystem.exceptions.*;
import rentalsystem.states.vehiclestates.*;

/**
 * State for when the station is full
 * @author nicolas.han.etu
 * @author fares.abdelli.etu
 */
public class FullState extends StationState {
	
	/**
	 * Full state class constructor
	 * @param s The station related to the state
	 */
	public FullState(Station s) {
		super(s);
	}
	
	/**
	 * Increases the number of intervals spent in this state
	 */
	@Override
	public void increaseNbIntervals() {
		this.nbIntervalsInState += 1;
	}
	
	/**
	 * Adds a vehicle to the station
	 * @throws StationIsFullException Exception raised because the station is full
	 */
	@Override
	public void addVehicle(Vehicle v) throws StationIsFullException{
		throw new StationIsFullException("The station is full");
	}
	
	/**
	 * Removes a Vehicle from the station, and sets the station to a MidState since it is not full anymore
	 * @throws VehicleNotInStationException Exception raised if the given vehicle is not in the station
	 */
	@Override
	public void removeVehicle(Vehicle v) throws VehicleNotInStationException{
		if (!this.station.getVehicles().contains(v)) {
			throw new VehicleNotInStationException("The given vehicle is not in the station");
		}
		this.station.getVehicles().remove(v);
		this.station.setState(new MidState(this.station));
		v.rent();
	}
}
