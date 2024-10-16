package rentalsystem.states.vehiclestates;

import rentalsystem.Vehicle;

/**
 * Class for the basic state of the vehicle (not stolen, not rented, not broken)
 */
public class IsOkState extends VehicleState {

	/**
	 * Constructor of the class
	 * @param vehicle The vehicle to which the state is related
	 */
	public IsOkState(Vehicle vehicle) {
		super(vehicle);
	}

	/**
	 * Steals the vehicle
	 */
	@Override
	public void steal() {
		System.out.println(this.vehicle+" was stolen");
		this.vehicle.setState(new StolenState(this.vehicle));
	}

	/**
	 * Breaks the vehicle
	 */
	@Override
	public void smash() {
		System.out.println(this.vehicle+" was broken");
		this.vehicle.setState(new BrokenState(this.vehicle));
	}

	/**
	 * Rents the vehicle
	 */
	@Override
	public void rent() {
		this.vehicle.setState(new RentedState(this.vehicle));
		this.vehicle.decreaseRentalsLeft();
	}
	
	/**
	 * Repairs the vehicle (does nothing because the vehicle is not being repaired)
	 */
	@Override
	public void repair() {
	}

}
