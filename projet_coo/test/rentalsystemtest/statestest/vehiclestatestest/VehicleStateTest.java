package rentalsystemtest.statestest.vehiclestatestest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rentalsystem.Vehicle;

public abstract class VehicleStateTest {

	protected Vehicle v;

	public abstract Vehicle createVehicle();

	@BeforeEach
	public void setUpBefore() {
		this.v = createVehicle();
	}

	@Test
	public abstract void smashTest();

	@Test
	public abstract void stealTest();

	@Test
	public abstract void rentTest();

	@Test
	public abstract void repairTest();

}