package rentalsystem.states.vehiclestates;

import rentalsystem.Vehicle;

/**
 * Class for a state representing a stolen vehicle
 */
public class StolenState extends VehicleState {

	/**
	 * Constructor of the class
	 * @param vehicle The vehicle to which the state is related
	 */
	public StolenState(Vehicle vehicle) {
		super(vehicle);
	}

	/**
	 * Steals the vehicle
	 * When in StolenState, vehicle cannot be stolen so nothing is done when method is called
	 */
	@Override
	public void steal(){
	}

	/**
	 * Breaks the vehicle
	 * When in StolenState, vehicle cannot be broken so nothing is done when method is called
	 */
	@Override
	public void smash() {
	}

	/**
	 * Rents the vehicle
	 * When in StolenState, vehicle cannot be rented so nothing is done when method is called
	 */
	@Override
	public void rent() {
	}
	
	/**
	 * Repairs the vehicle (does nothing because the vehicle is not being repaired)
	 */
	@Override
	public void repair() {
	}

}
