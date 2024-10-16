package rentalsystemtest.statestest.stationstatestest;

import rentalsystem.*;
import rentalsystem.exceptions.*;
import rentalsystem.rearrangements.RearrangementMethod;
import rentalsystem.rearrangements.RoundRobin;
import rentalsystem.states.stationstates.*;
import rentalsystem.states.vehiclestates.*;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import rentalsystem.states.stationstates.FullState;
import rentalsystemtest.statestest.vehiclestatestest.MockVehicle;

public class FullStateTest extends StationStateTest {
	protected MockVehicle mockv = new MockVehicle(1);

	@BeforeEach
	public void setUpBefore() {
		super.setUpBefore();
		this.s.setState(new FullState(s));
	}

	@Test
	public void increaseNbIntervalsTest() {
		assertTrue(s.getState().getNbIntervalsInState() == 0);
		s.getState().increaseNbIntervals();
		assertTrue(s.getState().getNbIntervalsInState() == 1);
	}

	@Test
	public void addVehicleTest() throws StationIsFullException, VehicleNotInStationException {
		assertThrows(StationIsFullException.class, () -> {
			s.putVehicle(new MockVehicle(1),false);
		});
	}

	@Test
	public void removeVehicleTest() throws StationIsFullException, VehicleNotInStationException {
		assertThrows(VehicleNotInStationException.class, () -> {
			s.popVehicle(mockv,false);
		});
		s.setState(new EmptyState(s));
		s.putVehicle(mockv,false);
		s.putVehicle(new MockVehicle(2),false);
		assertEquals(s.getVehicles().size(), 2);
		s.setState(new FullState(s));
		s.popVehicle(mockv,false);
		assertTrue(s.getState() instanceof MidState);
		assertTrue(mockv.getState() instanceof RentedState);

	}

}
