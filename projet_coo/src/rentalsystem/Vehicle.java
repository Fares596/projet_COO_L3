package rentalsystem;
import rentalsystem.states.vehiclestates.IsOkState;
import rentalsystem.states.vehiclestates.VehicleState;
import rentalsystem.worker.*;

/**
 * Vehicle class
 * @author nicolas.han.etu
 *
 */
public abstract class Vehicle{

	/**
	 * The maximum number of rentals before the vehicle gets broken
	 */
	public static final int NB_RENTALS_MAX=10;
	/**
	 * The vehicle's current state
	 */
	protected VehicleState state;
	/**
	 * The id of the vehicle
	 */
	protected int id;
	/**
	 * The number of rentals left until the vehicle gets broken
	 */
	protected int nbOfRentalsLeft;
	/**
	 * The number of intervals the vehicle has spent alone in the station
	 */
	protected int nbIntervalsAlone;
	
	/**
	 * Vehicle class constructor
	 * @param id The ID of the vehicle
	 */
	public Vehicle(int id) {
		this.nbOfRentalsLeft = NB_RENTALS_MAX;
		this.state = new IsOkState(this);
		this.id = id;
		this.nbIntervalsAlone=0;
	}

	/**
	 * Accesses the id of the vehicle
	 * @return The id of the vehicle
	 */
	public int getId(){
		return this.id;
	}

	/**
	 * Accesses the number of rentals left before the vehicle gets broken
	 * @return The number of rentals left before the vehicle gets broken
	 */
	public int getNbRentalsLeft(){
		return this.nbOfRentalsLeft;
	}
	
	/**
	 * Determines the state of the vehicle
	 * @return A State corresponding to the vehicle's state (e.g StolenState if stolen)
	 */
	public VehicleState getState() {
		return this.state;
	}
	
	/**
     * Accesses the number of intervals spent alone in the station 
	 * @return The number of intervals spent alone
	 */
	public int getNbIntervalsAlone() {
		return this.nbIntervalsAlone;
	}
	
	/**
	 * Decreases the number of rentals left until the vehicle gets broken by 1
	 */
	public void decreaseRentalsLeft() {
		this.nbOfRentalsLeft -- ;
	}
	
	/**
	 * Sets the state of the vehicle
	 * @param newState The new state of the vehicle
	 */
	public void setState(VehicleState newState) {
		this.state=newState;
	}
	
	/**
	 * Sets the number of intervals spent alone in the station to a new value
	 * @param n The new value of intervals spent alone
	 */
	public void setNbIntervalsAlone(int n) {
		this.nbIntervalsAlone=n;
	}

	/**
	 * Sets the number of rentals left until the vehicle gets broken to a new value
	 * @param n The new number of rentals left until the vehicle gets broken
	 */
	public void setNbRentalsLeft(int n){
		this.nbOfRentalsLeft=n;
	}
	
	/**
	 * Breaks the vehicle
	 */
	public void smash() {
		this.state.smash();
	}
	
	/**
	 * Rents the vehicle
	 */
	public void rent() {
		this.state.rent();
	}
	
	/**
	 * Steals the vehicle
	 */
	public void steal() {
		this.state.steal();
	}
	
	/**
	 * Repairs the vehicle
	 */
	public void repair() {
		this.state.repair();
	}
	
	/**
	 * Increases the number of intervals alone in the station
	 */
	public void increaseNbIntervalsAlone() {
		this.nbIntervalsAlone++;
	}
	
	/**
	 * Accepts the Worker so it can apply its effect on the vehicle
	 * @param w The worker that is accepted
	 */
	public void accept(Worker w) {
		w.effect(this);
	}

	/**
	 * toString method for the station
	 */
	public abstract String toString();
}