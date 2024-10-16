package rentalsystemtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rentalsystem.ControlCenter;
import rentalsystem.Interval;
import rentalsystem.Station;
import rentalsystem.exceptions.StationIsFullException;
import rentalsystem.exceptions.StationNotManagedException;
import rentalsystem.exceptions.VehicleNotInStationException;
import rentalsystem.rearrangements.RearrangementMethod;
import rentalsystem.rearrangements.RoundRobin;
import rentalsystem.states.vehiclestates.IsOkState;
import rentalsystem.states.vehiclestates.RepairingState;
import rentalsystemtest.statestest.vehiclestatestest.MockVehicle;

public class ControlCenterTest {
	
	protected ControlCenter<MockVehicle> cc;
	protected RearrangementMethod<MockVehicle> method;
	protected List<Station<MockVehicle>> stations;
	protected MockVehicle mockv = new MockVehicle(0);
	
	@BeforeEach
	public void setUpBefore() {
		this.method = new RoundRobin<MockVehicle>();
		this.stations = new ArrayList<Station<MockVehicle>>();
		for (int i = 0; i < 10; i++ ) {
			stations.add(new Station<MockVehicle>(10,i));
		}
	this.cc = new ControlCenter<MockVehicle>(stations,method);

	}
	
	@Test
	public void goodInit() {
		assertEquals(cc.getStations(), stations);
		assertEquals(cc.getMethod(), method);
		assertTrue(cc.getInterval().getNbActionsLeft() <= Interval.NB_MAX_ACTIONS);
		assertTrue(cc.getInterval().getNbActionsLeft() > 0);
	}
	
	@Test
	public void SetTest() {
		assertEquals(cc.getMethod(),method);
		assertEquals(cc.getStations(), stations);
		cc.setStations(null);
		cc.setMethod(null);
		assertTrue(cc.getMethod() == null);
		assertTrue(cc.getStations() == null);
	}
	
	@Test
	public void addStationTest() {
		assertEquals(cc.getStations().size(),10);
		Station<MockVehicle> newS = new Station<MockVehicle>(10,10);
		assertFalse(cc.getStations().contains(newS));
		cc.addStation(newS);
		assertTrue(cc.getStations().contains(newS));
		assertEquals(cc.getStations().size(),11);
	}
	
	@Test
	public void removeStationTestWhenStationInCC() throws StationNotManagedException {
		assertEquals(cc.getStations().size(),10);
		Station<MockVehicle> removedS = cc.getStations().get(0);
		assertTrue(cc.getStations().contains(removedS));
		cc.removeStation(removedS);
		assertFalse(cc.getStations().contains(removedS));
	}
	
	
	@Test
	public void removeStationTestWhenStationNotInCC() throws StationNotManagedException {
		assertThrows(StationNotManagedException.class, () ->
		{cc.removeStation(new Station<MockVehicle>(10,12312));});
	}
	
	@Test
	public void decreaseIntervalTest() throws VehicleNotInStationException, StationIsFullException {
		cc.createInterval(7); // we force >1 so decreaseInterval will not put it to 0 instantly
		int currentNbActionsLeft = cc.getInterval().getNbActionsLeft(); 
		cc.decreaseInterval();
		assertEquals(cc.getInterval().getNbActionsLeft(), currentNbActionsLeft-1);
		
	
		Station<MockVehicle> s1 = cc.getStations().get(0);
		Station<MockVehicle> s2 = cc.getStations().get(1);
		mockv.setState(new RepairingState(mockv));
		s1.putVehicle(mockv, false);
		
		while(cc.getInterval().getNbActionsLeft() > 1) {
			cc.decreaseInterval();
		}
		cc.decreaseInterval(); //decreases the actions left to 0 => new interval is created
		assertTrue(cc.getInterval().getNbActionsLeft() > 0);
		assertTrue(cc.getInterval().getNbActionsLeft() <= Interval.NB_MAX_ACTIONS);
		
		//s2 is empty, so intervalInState must have been improved by 1
		assertEquals(s2.getState().getNbIntervalsInState(), 1);
		
		//mockv in s1 is alone and in RepairingState, so he must be repaired, and intervalAlone improved
		assertEquals(mockv.getNbIntervalsAlone(),1);
		assertTrue(mockv.getState() instanceof IsOkState);

	}
	
	@Test
	public void rearrangeCallsTheMethodRearrangement() throws VehicleNotInStationException{
		MockRearrange<MockVehicle> newM = new MockRearrange<MockVehicle>();
		cc.setMethod(newM);
		assertEquals(0, newM.getNbOfCalls() );
		cc.rearrange();
		assertEquals(1, newM.getNbOfCalls() );
	}
	
	@Test
	public void checkForRearrangementTest() throws VehicleNotInStationException{
		MockRearrange<MockVehicle> newM = new MockRearrange<MockVehicle>();
		cc.setMethod(newM);
		cc.checkForRearrangements();
		assertEquals(0, newM.getNbOfCalls() ); // no need to rearrange at this point
		
		Station<MockVehicle> s1 = cc.getStations().get(0);
		s1.getState().increaseNbIntervals();
		s1.getState().increaseNbIntervals(); //s1 has spent 2 intervals being empty
		cc.checkForRearrangements();
		assertEquals(1, newM.getNbOfCalls());//so now we need a rearrangement

		
	}
}
