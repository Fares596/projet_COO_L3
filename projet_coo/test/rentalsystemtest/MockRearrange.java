package rentalsystemtest;

import java.util.List;

import rentalsystem.Station;
import rentalsystem.Vehicle;
import rentalsystem.exceptions.VehicleNotInStationException;
import rentalsystem.rearrangements.RearrangementMethod;

public class MockRearrange<T extends Vehicle> implements RearrangementMethod<T> {
	public int rearrangeCalled = 0;
	
		
	public MockRearrange() {
	}
	
	
	@Override
	public void rearrange(List<Station<T>> stations) throws VehicleNotInStationException {
		rearrangeCalled ++;
	}
	
	
	public int getNbOfCalls() {
		return rearrangeCalled;
	}
}
