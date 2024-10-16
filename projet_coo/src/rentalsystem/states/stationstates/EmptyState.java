package rentalsystem.states.stationstates;
import rentalsystem.*;
import rentalsystem.exceptions.*;
import rentalsystem.states.vehiclestates.*;

/**
 * Class for an empty state of stations
 * @author nicolas.han.etu
 * @author fares.abdelli.etu
 */


public class EmptyState extends StationState {

	/**
	 * Empty state class constructor
	 * @param s The station related to the state
	 */
	public EmptyState(Station s) {
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
	 */
	@Override
	public void addVehicle(Vehicle v) throws StationIsFullException{
		this.station.getVehicles().add(v);
		this.station.setState(new MidState(this.station));
		if (v.getState() instanceof RentedState){
			v.setState(new IsOkState(v));
		}
		if (v.getNbRentalsLeft()<=0){
			v.smash();
		}
	}
	
	/**
	 * Removes a vehicle from the station
	 * @throws VehicleNotInStationException Exception raised because the station is empty
	 */
	@Override
	public void removeVehicle(Vehicle v) throws VehicleNotInStationException{
		throw new VehicleNotInStationException("The station is empty");
	}

}
