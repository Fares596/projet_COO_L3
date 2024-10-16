package rentalsystem.states.vehiclestates;

import rentalsystem.Vehicle;

/**
 * Class for when the vehicle is rented
 * @author nicolas.han.etu
 *
 */
public class RentedState extends VehicleState {

	/**
	 * Constructor of the RentedState class
	 * @param vehicle the vehicle to which the state is related
	 */
	public RentedState(Vehicle vehicle) {
		super(vehicle);
	}

	/**
	 * Steals the vehicle
	 * When in RentedState, vehicle cannot be stolen, so nothing is done
	 */
	@Override
	public void steal(){
	}

	/**
	 * Breaks the vehicle
	 * It is possible for a rented vehicle to get broken by its user, which is why the method does something
	 */
	@Override
	public void smash() {
		System.out.println(this.vehicle+" was broken");
		this.vehicle.setState(new BrokenState(this.vehicle));
	}

	/**
	 * Rents the vehicle
	 * When in RentedState, vehicle cannot be rented, so nothing is done
	 */
	@Override
	public void rent() {
	}
	
	/**
	 * Repair the vehicle (does nothing because the vehicle is not being repaired)
	 */
	@Override
	public void repair() {
	}

}
