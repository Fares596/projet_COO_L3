package rentalsystemtest.statestest.stationstatestest;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;

import rentalsystem.ControlCenter;
import rentalsystem.Station;
import rentalsystem.exceptions.StationIsFullException;
import rentalsystem.exceptions.VehicleNotInStationException;
import rentalsystem.rearrangements.RearrangementMethod;
import rentalsystem.rearrangements.RoundRobin;
import rentalsystem.states.stationstates.*;
import rentalsystem.states.vehiclestates.BrokenState;
import rentalsystem.states.vehiclestates.IsOkState;
import rentalsystem.states.vehiclestates.RentedState;
import rentalsystem.states.vehiclestates.RepairingState;
import rentalsystemtest.statestest.vehiclestatestest.MockVehicle;

public class EmptyStateTest extends StationStateTest {
	protected MockVehicle mockv = new MockVehicle(1);

	@BeforeEach
	public void setUpBefore() {
		super.setUpBefore();
		this.s.setState(new EmptyState(s));
	}

	@Test
	public void increaseNbIntervalsTest() {
		assertTrue(s.getState().getNbIntervalsInState() == 0);
		s.getState().increaseNbIntervals();
		assertTrue(s.getState().getNbIntervalsInState() == 1);
	}

	@Test
	public void addVehicleTest() throws StationIsFullException, VehicleNotInStationException {
		mockv.setState(new RentedState(mockv));
		s.putVehicle(mockv,false);
		assertTrue(s.getVehicles().contains(mockv));
		assertTrue(s.getState() instanceof MidState);
		assertTrue(mockv.getState() instanceof IsOkState);
		// breaking the bike
		
		while(mockv.getNbRentalsLeft() > 0) {
			mockv.decreaseRentalsLeft();
		}
		s.putVehicle(mockv,false);
		assertTrue(mockv.getState() instanceof RepairingState);

	}

	@Test
	public void removeVehicleTest() throws StationIsFullException, VehicleNotInStationException {
		assertThrows(VehicleNotInStationException.class, () -> {
			s.popVehicle(mockv,false);
		});

	}

}
