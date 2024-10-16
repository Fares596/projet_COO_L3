package rentalsystem;

import java.util.List;
import java.util.ArrayList;
import rentalsystem.states.stationstates.EmptyState;
import rentalsystem.states.stationstates.FullState;
import rentalsystem.states.stationstates.StationState;
import rentalsystem.exceptions.*;
/**
 * Station class
 * @author nicolas.han.etu
 * @author fares.abdelli.etu
 *
 */
public class Station <T extends Vehicle>{
	
	/**
	 * The minimum capacity of the station
	 */
	public static final int NB_MIN_SPOTS=10;
	/**
	 * The maximum capacity of the station
	 */
	public static final int NB_MAX_SPOTS=20;
	private static final int NB_INTERVALS_UNTIL_STOLEN=2;
	private int capacity;
	private int id;
	private List<T> vehicles;
	private StationState state;
	private ControlCenter<T> controlcenter;
	
	/**
	 * Station class constructor
	 * @param cap The number of vehicles the station can handle
	 * @param id The id of the station
	 */
	public Station (int cap, int id){
		this.capacity = cap;
		this.state = new EmptyState(this) ;
		this.id=id;
		this.vehicles = new ArrayList<T>();
	}
	
	/**
	 * Accesses the current state of the station
	 * @return The current state of the station
	 */
	public StationState getState() {
		return this.state;
	}
	
	/**
	 * Accesses the id of the station
	 * @return The id of the station
	 */
	public int getId() {
		return this.id;

	}
	
	/**
	 * Accesses the list of vehicles
	 * @return The list of vehicles in the station
	 */
	public List<T> getVehicles(){
		return this.vehicles;
	}
	
	/**
	 * Accesses the capacity of the station
	 * @return The capacity of the station
	 */
	public int getCapacity() {
		return this.capacity;
	}
	
	/**
	 * Accesses the control center that manages the station
	 * @return The ControlCenter attribute
	 */
	public ControlCenter<T> getControlCenter() {
		return this.controlcenter;
	}
	
	/**
	 * Sets the state of the station to a new state
	 * @param newState The new state of the station
	 */
	public void setState(StationState newState) {
		this.state = newState;
	}
	
	/**
	 * Sets the ControlCenter attribute to a new values
	 * @param cc The new value of ControlCenter
	 */
	public void setControlCenter(ControlCenter<T> cc) {
		this.controlcenter=cc;
	}
	
	/**
	 * Puts a vehicle in the station
	 * @param vehicle The vehicle to put in the station
	 * @param notify true if the method should notify the control center, false otherwise
	 * @throws StationIsFullException Exception raised if the station is full
	 * @throws VehicleNotInStationException Should not happen (cf rearrange method documentation)
	 */
	public void putVehicle(T vehicle, boolean notify) throws StationIsFullException, VehicleNotInStationException{
		if (notify) {
			this.notifyControlCenter(false, vehicle);
		}
		this.state.addVehicle(vehicle);
		// Sets the station's state to a FullState if it is full after adding the vehicle
		if (this.vehicles.size()==this.capacity) {
			this.setState(new FullState(this));
		}
	}
	
	/**
	 * Pops a vehicle from the station
	 * @param vehicle The vehicle to pop from the station
	 * @param notify true if the method should notify the control center, false otherwise
	 * @throws VehicleNotInStationException Exception raised if the given vehicle is not in the station
	 */
	public void popVehicle(T vehicle, boolean notify) throws VehicleNotInStationException	{
		this.state.removeVehicle(vehicle);
		if (notify) {	
			this.notifyControlCenter(true, vehicle);
		}
		
		// If the station is empty after removing the vehicle, sets the state to empty
		if (this.vehicles.size()==0) {
			this.setState(new EmptyState(this));
		}
	}
		
	/**
	 * Manages the case where a Vehicle is alone (or not) in the station
	 */
	public void checkLoneliness() {
		// If there is only one vehicle in the station, we increase the number of intervals spent alone and if it gets greater than 2, it gets stolen
		if (this.vehicles.size()==1) {
			this.vehicles.get(0).increaseNbIntervalsAlone();
			if (this.vehicles.get(0).getNbIntervalsAlone()>=Station.NB_INTERVALS_UNTIL_STOLEN) {
				this.vehicles.get(0).steal();
				this.vehicles.remove(0);
			}
		}
		// If the station contains more than 1 vehicle, then the number of intervals spent alone is set to 0 for each of them
		else {
			for (Vehicle v:this.vehicles) {
				v.setNbIntervalsAlone(0);
			}
		}
	}
	
	/**
	 * Notifies the ControlCenter that a change has been made within the station
	 * @param isRented true if the control center is being notify for a rental, false otherwise
	 * @param vehicle the vehicle that is being rented/given back
	 * @throws VehicleNotInStationException Should not happen (cf rearrange method documentation)
	 */
	public void notifyControlCenter(boolean isRented, Vehicle vehicle) throws VehicleNotInStationException{
		this.controlcenter.update(isRented, vehicle, this);
	}
	
	/**
	 * toString method for the class
	 * @return A String representing the station
	 */
	public String toString() {
		return "Station "+this.id;
	}
}
