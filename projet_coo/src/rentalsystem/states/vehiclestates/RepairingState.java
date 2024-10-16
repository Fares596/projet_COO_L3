package rentalsystem.states.vehiclestates;
import rentalsystem.Vehicle;

/**
 * Class representing the state where the vehicle is being repaired
 * @author nicolas.han.etu
 *
 */
public class RepairingState extends VehicleState {

	/**
	 * Constructor of the class
	 * @param vehicle The vehicle that is being repaired
	 */
	public RepairingState(Vehicle vehicle) {
		super(vehicle);
	}
	
	/**
	 * Steals the vehicle (does nothing in this case because the vehicle is being repaired)
	 */
	@Override
	public void steal() {
		
	}

	/**
	 * Breaks the vehicle (does nothing in this case because the vehicle is being repaired)
	 */
	@Override
	public void smash() {
	}

	/**
	 * Rents the vehicle (does nothing in this case because the vehicle is being repaired)
	 */
	@Override
	public void rent() {
	}
	
	/**
	 * Repairs the vehicle
	 */
	@Override
	public void repair() {
		System.out.println(this.vehicle+" was repaired");
		this.vehicle.setState(new IsOkState(this.vehicle));
		this.vehicle.setNbRentalsLeft(Vehicle.NB_RENTALS_MAX);
	}

}
