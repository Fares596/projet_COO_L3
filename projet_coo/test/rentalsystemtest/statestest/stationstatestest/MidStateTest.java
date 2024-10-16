package rentalsystemtest.statestest.stationstatestest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rentalsystem.ControlCenter;
import rentalsystem.exceptions.StationIsFullException;
import rentalsystem.exceptions.VehicleNotInStationException;
import rentalsystem.states.stationstates.*;
import rentalsystem.states.vehiclestates.*;
import rentalsystemtest.statestest.vehiclestatestest.MockVehicle;

public class MidStateTest extends StationStateTest {
	protected MockVehicle mockv = new MockVehicle(1);
	protected ControlCenter<MockVehicle> cc;

	@BeforeEach
	public void setUpBefore() {
		super.setUpBefore();
		this.s.setState(new MidState(s));

	}

	@Test
	public void increaseNbIntervalsTest() {
		assertTrue(s.getState().getNbIntervalsInState() == 0);
		s.getState().increaseNbIntervals();
		assertTrue(s.getState().getNbIntervalsInState() == 0);
	}

	@Test
	public void addVehicleTest() throws StationIsFullException, VehicleNotInStationException {
		mockv.setState(new RentedState(mockv));
		s.putVehicle(mockv,false);
		assertTrue(s.getVehicles().contains(mockv));
		assertTrue(mockv.getState() instanceof IsOkState);
		
		// breaking the bike
		while(mockv.getNbRentalsLeft() > 0) {
			mockv.decreaseRentalsLeft();
		}
		s.putVehicle(mockv,false);
		// hopefully the repairer is coming
		assertTrue(mockv.getState() instanceof RepairingState);

	}

	@Test
	public void removeVehicleTest() throws StationIsFullException, VehicleNotInStationException {
		assertThrows(VehicleNotInStationException.class, () -> {
			s.popVehicle(mockv,false);
		});
		s.putVehicle(mockv,false);
		s.putVehicle(new MockVehicle(2),false);
		s.popVehicle(mockv,false);
		assertFalse(s.getVehicles().contains(mockv));
		assertTrue(mockv.getState() instanceof RentedState);
	}

}
