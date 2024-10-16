package rentalsystemtest;

import rentalsystem.Vehicle;
import rentalsystem.states.vehiclestates.*;
import rentalsystem.worker.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class VehicleTest {

	protected Vehicle v;

	public abstract Vehicle createVehicle();

	protected Worker w = new MockWorker("Mario");

	@BeforeEach
	public void setUpBefore() {
		this.v = createVehicle();
	}

	@Test
	public void goodInit() {
		assertTrue(this.v.getState() instanceof IsOkState);
		assertEquals(Vehicle.NB_RENTALS_MAX, this.v.getNbRentalsLeft());
	}

	@Test
	public void setStateTest() {
		assertTrue(this.v.getState() instanceof IsOkState);
		this.v.setState(new BrokenState(this.v));
		assertTrue(this.v.getState() instanceof BrokenState);
	}

	@Test
	public void brokenIsntAvailable() {
		this.v.smash();
		assertTrue(this.v.getState() instanceof BrokenState);
	}

	@Test
	public void increaseNbIntervals() {
		assertEquals(v.getNbIntervalsAlone(), 0);
		v.increaseNbIntervalsAlone();
		assertEquals(v.getNbIntervalsAlone(), 1);
	}

	@Test
	public void NbIntervalsAlone() {
		assertEquals(v.getNbIntervalsAlone(), 0);
		v.increaseNbIntervalsAlone();
		assertEquals(v.getNbIntervalsAlone(), 1);

	}

}
