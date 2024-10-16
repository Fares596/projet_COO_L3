package rentalsystem.rearrangements;

import java.util.ArrayList;
import java.util.List;

import rentalsystem.Station;
import rentalsystem.Vehicle;
import rentalsystem.exceptions.*;

/**
 * Class for the round robin rearrangement method
 * @author nicolas.han.etu
 *
 */
public class RoundRobin<T extends Vehicle> implements RearrangementMethod<T> {

	/**
	 * Constructor of the class
	 */
	public RoundRobin() {
	}
	
	/**
	 * Rearranges the vehicles in the stations
	 * @param stations The list of stations that are being rearranged
	 */
	@Override
	public void rearrange(List<Station<T>> stations) throws VehicleNotInStationException {
		System.out.println("Rearrangement method: Round Robin");
		// We put every vehicle of every station in a single list
		List<T> allVehicles=new ArrayList<T>();
		for (Station<T> s : stations){
			while (!s.getVehicles().isEmpty()) {
				T v;
				v=s.getVehicles().get(0);
				allVehicles.add(v);
				s.getVehicles().remove(v);
			}	
		}
		
		// We distribute all the vehicles from the list into all of the stations
		while (!(allVehicles.isEmpty())){
			boolean exceptionThrown=false;
			T v=null;
			for (Station<T> s : stations){
				if (!allVehicles.isEmpty()){
					// If an exception was thrown during the previous try then the vehicle v must still be placed in a station before replacing its value with the next one
					if (!exceptionThrown){
						v=allVehicles.remove(0);
					}
					// Stations don't necessarily have the same capacity, therefore it is possible to add a vehicle to a full station, which throws an exception
					try{
						s.putVehicle(v, false);
						exceptionThrown=false;
					}
					catch (StationIsFullException e){
						exceptionThrown=true;
					}
				}
			}
		}
		System.out.println("\nNew arrangement of the stations:");
		for (Station<T> s:stations){
			System.out.println(s);
			for (T v: s.getVehicles()){
				System.out.println("\t"+v);
			}
		}
		System.out.println("\n");
	}

}
