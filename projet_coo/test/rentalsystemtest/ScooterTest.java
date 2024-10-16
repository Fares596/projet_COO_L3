package rentalsystemtest;

import rentalsystem.*;

public class ScooterTest extends VehicleTest {

	@Override
	public Vehicle createVehicle() {
		return new Scooter(1);
	}

}