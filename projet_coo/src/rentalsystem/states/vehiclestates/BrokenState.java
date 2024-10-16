package rentalsystem.states.vehiclestates;

import rentalsystem.Vehicle;

/**
 * Class for the broken state of vehicles
 * @author nicolas.han.etu
 *
 */
public class BrokenState extends VehicleState {

	/**
	 * BrokenState class constructor
	 * @param vehicle The vehicle linked to the new instance of BrokenState
	 */
	public BrokenState(Vehicle vehicle) {
		super(vehicle);
	}

	/**
	 * Steals the vehicle (does nothing in this case because nobody would steal a broken vehicle)
	 */
	@Override
	public void steal() {
	}

	/**
	 * Breaks the vehicle (does nothing in this case because the vehicle is already broken)
	 */
	@Override
	public void smash() {
	}

	/**
	 * Rents the vehicle (does nothing in this case because the vehicle is broken)
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
