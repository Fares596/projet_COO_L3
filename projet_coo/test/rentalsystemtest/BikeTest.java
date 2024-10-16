package rentalsystemtest;

import rentalsystem.*;

public class BikeTest extends VehicleTest {

	@Override
	public Vehicle createVehicle() {
		return new Bike(1);
	}

}