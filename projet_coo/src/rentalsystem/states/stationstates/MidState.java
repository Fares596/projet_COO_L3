package rentalsystem.states.stationstates;
import rentalsystem.*;
import rentalsystem.exceptions.*;
import rentalsystem.states.vehiclestates.*;
import rentalsystem.worker.Repairer;


/**
 * State for when the station is neither full nor empty it's all good and we love it
 * @author nicolas.han.etu
 * @author fares.abdelli.etu
 */
public class MidState extends StationState {

	/**
	 * MidState class constructor
	 * @param s The station related to the state
	 */
	public MidState(Station s ) {
		super(s);
		
	}
	
	/**
	 * Increases the number of the intervals spent in this state
	 */
	@Override
	public void increaseNbIntervals() {
		this.nbIntervalsInState = 0;
	}
	
	/**
	 * Adds a vehicle to the station
	 * @param v The vehicle to add to the station
	 */
	@Override
	public void addVehicle(Vehicle v) throws StationIsFullException{
		this.station.getVehicles().add(v);
		if (v.getState() instanceof RentedState){
			v.setState(new IsOkState(v));
		}
		if (v.getNbRentalsLeft()<=0){
			v.smash();
			Repairer r = new Repairer("Philippe");
			r.visit(v);
		}
	}
	
	/**
	 * Removes a vehicle from the station
	 * @param v The vehicle that is removed from the station if possible
	 * @throws VehicleNotInStationException Exception raised if the given vehicle is not in the station
	 */
	@Override
	public void removeVehicle(Vehicle v) throws VehicleNotInStationException{
		if (!this.station.getVehicles().contains(v)) {
			throw new VehicleNotInStationException("The given vehicle is not in the station");
		}
		this.station.getVehicles().remove(v);
		v.rent();
	}

}
