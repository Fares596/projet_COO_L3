package rentalsystemtest.statestest.vehiclestatestest;

import rentalsystem.Vehicle;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import rentalsystem.states.vehiclestates.*;

public class IsOkStateTest extends VehicleStateTest {

	@Override
	public Vehicle createVehicle() {
		Vehicle v = new MockVehicle(20);
		v.setState(new IsOkState(v));
		return v;
	}

	@Test
	public void smashTest() {
		assertTrue(v.getState() instanceof IsOkState);
		v.smash();
		assertTrue(v.getState() instanceof BrokenState);
	}

	@Test
	public void stealTest() {
		assertTrue(v.getState() instanceof IsOkState);
		v.steal();
		assertTrue(v.getState() instanceof StolenState);

	}

	@Test
	public void rentTest() {
		assertTrue(v.getState() instanceof IsOkState);
		v.rent();
		assertTrue(v.getState() instanceof RentedState);
		// everything goes well
	}

	@Test
	/* does nothing */
	public void repairTest() {
		assertTrue(v.getState() instanceof IsOkState);
		v.repair();
		assertTrue(v.getState() instanceof IsOkState);

	}

}
