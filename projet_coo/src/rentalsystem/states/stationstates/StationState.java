package rentalsystem.states.stationstates;
import rentalsystem.*;
import rentalsystem.exceptions.*;

/**
* State
* @author fares.abdelli.etu
*/
public abstract class StationState {
	
	/**
	 * The number of intervals the station has spent in this state
	 */
	protected int nbIntervalsInState;
	/**
	 * The station to which the state is related
	 */
	protected Station<Vehicle> station;
	
	/**
	 * Constructor of the class
	 * @param s The station to which the state is related
	 */
	public StationState(Station s) {
		this.station = s;
		this.nbIntervalsInState=0;
	}

	/**
	 * Accesses the number of intervals the station has spent in the state
	 * @return The number of intervals the station has spent in the state
	 */
	public int getNbIntervalsInState(){
		return this.nbIntervalsInState;
	}
	
	/**
	 * Increases the number of intervals spent in this state
	 */
	public abstract void increaseNbIntervals();
	
	/**
	 * Adds a vehicle to the station
	 * @param v The vehicle to add
	 * @throws StationIsFullException Exception raised if the station is full
	 */
	public abstract void addVehicle(Vehicle v) throws StationIsFullException;
	
	/**
	 * Removes a vehicle from the station
	 * @param v The vehicle to remove
	 * @throws VehicleNotInStationException Exception raised if the vehicle is not in the station
	 */
	public abstract void removeVehicle(Vehicle v) throws VehicleNotInStationException;

}
