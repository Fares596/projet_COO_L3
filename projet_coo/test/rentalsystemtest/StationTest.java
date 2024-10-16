package rentalsystemtest;

import rentalsystem.*;
import rentalsystem.exceptions.*;
import rentalsystem.rearrangements.RearrangementMethod;
import rentalsystem.rearrangements.RoundRobin;
import rentalsystem.states.stationstates.*;
import rentalsystem.states.vehiclestates.*;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import rentalsystemtest.statestest.vehiclestatestest.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StationTest {

	protected Station<MockVehicle> s;
	protected MockVehicle mockv;
	protected ControlCenter<MockVehicle> cc;

	
	@BeforeEach
	public void setUpBefore() {
		this.s = new Station<MockVehicle>(20, 1);
		this.mockv = new MockVehicle(1);
		List<Station<MockVehicle>> stations = new ArrayList<Station<MockVehicle>>();
		stations.add((Station<MockVehicle>) s);
		RearrangementMethod<MockVehicle> method = new RoundRobin<MockVehicle>();
		this.cc = new ControlCenter<MockVehicle>(stations, method);
		cc.createInterval(2);
		s.setControlCenter(cc);
	}

	@Test
	public void goodInit() {
		assertEquals(s.getId(), 1);
		assertEquals(s.getCapacity(), 20);
		assertTrue(s.getState() instanceof EmptyState);
	}

	@Test
	public void getAndSetState() {
		assertTrue(s.getState() instanceof EmptyState);
		s.setState(new FullState(s));
		assertTrue(s.getState() instanceof FullState);
	}

	@Test
	public void getAndSetControlCenter() {
		s.setControlCenter(null);
		assertTrue(s.getControlCenter() == null);
		s.setControlCenter(cc);
		assertTrue(s.getControlCenter() == cc);
	}

	@Test
	public void putVehicleTest() throws VehicleNotInStationException, StationIsFullException {
		assertTrue(s.getVehicles().size() == 0);
		s.putVehicle(mockv,false);
		assertTrue(s.getVehicles().size() == 1);
		assertTrue(s.getState() instanceof MidState);
	}

	@Test
	public void putVehicleUntilFullTest() throws VehicleNotInStationException, StationIsFullException {
		for (int i = 0; i < s.getCapacity() - 1; i++) {
			s.putVehicle(mockv,false);
		}
		assertTrue(s.getState() instanceof MidState);
		s.putVehicle(mockv,false);
		assertTrue(s.getState() instanceof FullState);
		/* we're trying to drop a Vehicle into a full Station ! */
		assertThrows(StationIsFullException.class, () -> {
			s.putVehicle(mockv,false);
		});

	}

	@Test
	public void popVehicleTest() throws VehicleNotInStationException, StationIsFullException {
		s.putVehicle(mockv,false);
		assertTrue(s.getVehicles().size() == 1);
		assertTrue(s.getState() instanceof MidState);
		s.popVehicle(mockv,false);
		assertTrue(s.getVehicles().size() == 0);
		assertTrue(s.getState() instanceof EmptyState);
		assertThrows(VehicleNotInStationException.class, () -> {
			s.popVehicle(mockv,false);
		});
	}

	@Test
	public void lonelinessIncreasesNbIntervalsAlone() throws VehicleNotInStationException, StationIsFullException {
		s.putVehicle(mockv,false);
		assertTrue(mockv.getNbIntervalsAlone() == 0);
		s.checkLoneliness();
		assertTrue(mockv.getNbIntervalsAlone() == 1);
		/* Vehicle has been alone for 2 intervals, so it's being stolen !! Nooo! */
		s.checkLoneliness();
		assertTrue(mockv.getState() instanceof StolenState);
		assertTrue(s.getVehicles().size() == 0);
	}

	@Test
	public void checkLonelinessWhenNotAlone() throws VehicleNotInStationException, StationIsFullException {
		s.putVehicle(mockv,false);
		assertTrue(mockv.getNbIntervalsAlone() == 0);
		s.checkLoneliness();
		assertTrue(mockv.getNbIntervalsAlone() == 1);
		MockVehicle mockv2 = new MockVehicle(2);
		s.putVehicle(mockv2,false);
		/* mockv is no more alone :) IntervalAlone should be zero now */
		s.checkLoneliness();
		assertTrue(mockv.getNbIntervalsAlone() == 0);

	}

}