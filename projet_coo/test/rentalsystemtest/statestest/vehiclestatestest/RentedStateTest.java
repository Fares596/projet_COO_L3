package rentalsystemtest.statestest.vehiclestatestest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import rentalsystem.Vehicle;
import rentalsystem.states.vehiclestates.*;

public class RentedStateTest extends VehicleStateTest {

	@Override
	public Vehicle createVehicle() {
		Vehicle v = new MockVehicle(20);
		v.setState(new RentedState(v));
		return v;
	}

	@Test
	public void smashTest() {
		assertTrue(v.getState() instanceof RentedState);
		v.smash();
		assertTrue(v.getState() instanceof BrokenState);
	}

	@Test
	public void stealTest() {
		assertTrue(v.getState() instanceof RentedState);
		v.steal();
		assertTrue(v.getState() instanceof RentedState);
	}

	@Test
	public void rentTest() {
		assertTrue(v.getState() instanceof RentedState);
		v.rent();
		assertTrue(v.getState() instanceof RentedState);
	}

	@Test
	public void repairTest() {
		assertTrue(v.getState() instanceof RentedState);
		v.repair();
		assertTrue(v.getState() instanceof RentedState);

	}

}
