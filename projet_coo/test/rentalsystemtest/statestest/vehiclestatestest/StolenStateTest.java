package rentalsystemtest.statestest.vehiclestatestest;

import rentalsystem.Vehicle;
import rentalsystem.states.vehiclestates.StolenState;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StolenStateTest extends VehicleStateTest {

	@Test
	public Vehicle createVehicle() {
		Vehicle v = new MockVehicle(20);
		v.setState(new StolenState(v));
		return v;
	}

	@Test
	public void smashTest() {
		assertTrue(v.getState() instanceof StolenState);
		v.smash();
		assertTrue(v.getState() instanceof StolenState);
	}

	@Test
	public void stealTest() {
		assertTrue(v.getState() instanceof StolenState);
		v.steal();
		assertTrue(v.getState() instanceof StolenState);
	}

	@Test
	public void rentTest() {
		assertTrue(v.getState() instanceof StolenState);
		v.rent();
		assertTrue(v.getState() instanceof StolenState);
	}

	@Test
	public void repairTest() {
		assertTrue(v.getState() instanceof StolenState);
		v.repair();
		assertTrue(v.getState() instanceof StolenState);
	}

}
