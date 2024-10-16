package rentalsystemtest.statestest.vehiclestatestest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import rentalsystem.Vehicle;
import rentalsystem.states.vehiclestates.*;

public class RepairingStateTest extends VehicleStateTest {

	@Override
	public Vehicle createVehicle() {
		Vehicle v = new MockVehicle(20);
		v.setState(new RepairingState(v));
		return v;
	}

	@Test
	public void smashTest() {
		assertTrue(v.getState() instanceof RepairingState);
		v.smash();
		assertTrue(v.getState() instanceof RepairingState);
	}

	@Test
	public void stealTest() {
		assertTrue(v.getState() instanceof RepairingState);
		v.steal();
		assertTrue(v.getState() instanceof RepairingState);

	}

	@Test
	public void rentTest() {
		assertTrue(v.getState() instanceof RepairingState);
		v.rent();
		assertTrue(v.getState() instanceof RepairingState);

	}

	@Test
	public void repairTest() {
		assertTrue(v.getState() instanceof RepairingState);
		v.repair();
		assertTrue(v.getState() instanceof IsOkState);

	}

}
