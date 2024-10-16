package rentalsystemtest.statestest.vehiclestatestest;

import org.junit.jupiter.api.Test;

import rentalsystem.Vehicle;
import rentalsystem.states.vehiclestates.*;
import static org.junit.jupiter.api.Assertions.*;

public class BrokenStateTest extends VehicleStateTest {

	@Override
	public Vehicle createVehicle() {
		Vehicle v = new MockVehicle(20);
		v.setState(new BrokenState(v));
		return v;
	}

	@Test
	public void smashTest() {
		assertTrue(v.getState() instanceof BrokenState);
		v.smash();
		assertTrue(v.getState() instanceof BrokenState);
	}

	@Test
	public void stealTest() {
		assertTrue(v.getState() instanceof BrokenState);
		v.steal();
		assertTrue(v.getState() instanceof BrokenState);

	}

	@Test
	public void rentTest() {
		assertTrue(v.getState() instanceof BrokenState);
		v.rent();
		assertTrue(v.getState() instanceof BrokenState);

	}

	@Test
	public void repairTest() {
		assertTrue(v.getState() instanceof BrokenState);
		v.repair();
		assertTrue(v.getState() instanceof BrokenState);

	}

}
