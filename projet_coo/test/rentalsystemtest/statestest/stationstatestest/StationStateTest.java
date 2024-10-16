package rentalsystemtest.statestest.stationstatestest;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import rentalsystem.*;
import rentalsystem.exceptions.*;
import rentalsystem.rearrangements.RearrangementMethod;
import rentalsystem.rearrangements.RoundRobin;
import rentalsystemtest.statestest.vehiclestatestest.MockVehicle;

public abstract class StationStateTest {

	protected Station<MockVehicle> s;
	protected ControlCenter<MockVehicle> cc;

	@BeforeEach
	public void setUpBefore() {
		this.s = new Station<MockVehicle>(20, 1);
		// creation of the control center of s (we're gonna need one)
		List<Station<MockVehicle>> stations = new ArrayList<Station<MockVehicle>>();
		stations.add((Station<MockVehicle>) s);
		RearrangementMethod<MockVehicle> method = new RoundRobin<MockVehicle>();
		this.cc = new ControlCenter<MockVehicle>(stations, method);
		cc.createInterval(2);
		s.setControlCenter(cc);
	}

	@Test
	public abstract void increaseNbIntervalsTest();

	@Test
	public abstract void addVehicleTest() throws StationIsFullException, VehicleNotInStationException;

	@Test
	public abstract void removeVehicleTest() throws StationIsFullException, VehicleNotInStationException;

}
