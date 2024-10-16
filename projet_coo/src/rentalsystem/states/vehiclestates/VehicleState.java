package rentalsystem.states.vehiclestates;

import rentalsystem.Vehicle;

/**
 * Abstract class representing Vehicle States
 * @author nicolas.han.etu
 *
 */
public abstract class VehicleState {
	/**
	 * Attribute of the class
	 */
	protected Vehicle vehicle;

	/**
	 * Constructor of the class
	 * @param vehicle The vehicle related to the state
	 */
	public VehicleState(Vehicle vehicle) {
		this.vehicle=vehicle;
	}
	
	/**
	 * Steals the vehicle
	 */
	public abstract void steal();
	
	/**
	 * Breaks the vehicle
	 */
	public abstract void smash();
	
	/**
	 * Rents the vehicle
	 */
	public abstract void rent();
	
	/**
	 * Repairs the vehicle
	 */
	public abstract void repair();
}
