package rentalsystem;
import rentalsystem.rearrangements.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rentalsystem.exceptions.*;
import rentalsystem.factories.*;
import rentalsystem.states.vehiclestates.*;

/**
 * Class for a main execution of the rental system
 */
public class RentalSystemMain {

	/**
	 * The main method
	 * @param args The arguments
	 * @throws StationIsFullException Exception raised when putting a vehicle in a station that is full
	 * @throws VehicleNotInStationException Exception raised when trying to rent a vehicle that is not in the station
	 */
	public static void main(String[] args) throws StationIsFullException, VehicleNotInStationException {
		Random rand = new Random();
		RearrangementMethod<Bike> bikeRearrangement = new RoundRobin<Bike>();
		// Creation of a list of stations with a random capacity between NB_MIN_SPOTS and NB_MAX_SPOTS (included)
		List<Station<Bike>> stations = new ArrayList<Station<Bike>>();
		for (int i=0; i<5; i++) {
			stations.add(new Station<Bike>(Station.NB_MIN_SPOTS+ rand.nextInt(Station.NB_MAX_SPOTS-Station.NB_MIN_SPOTS+1),i));
		}
		// Creation of the ControlCenter
		ControlCenter<Bike> controlcenter = new ControlCenter<Bike>(stations,bikeRearrangement);
		// Creation of vehicles to put in the stations
		for (Station<Bike> s:stations) {
			System.out.println(s);
			for (Bike b:s.getVehicles()) {
				System.out.println("\t"+b);
			}
			s.setControlCenter(controlcenter);
		}
		BikeFactory bfact=new BikeFactory();
		for (int i=0; i<5;i++) {
			for (Station<Bike> s:controlcenter.getStations()) {
				s.putVehicle(bfact.createVehicle(), false);
			}
		}
		
		// Make it run for a bunch of turns
		List<Bike> rentedBikes = new ArrayList<Bike>(); // A list in which we put the rented bikes in order to not lose them
		for (int j=0;j<50;j++){
			// Choice of a number of vehicles to rent/to give back
			int nbRentals = rand.nextInt(10);
			int nbGiveBacks = 0;
			if (rentedBikes.size()>0){
				nbGiveBacks = rand.nextInt(rentedBikes.size()+1);
			}
			// Rents a vehicle nbRentals times
			for (int i=0;i<nbRentals;i++){
				// Checks whether or not all of the stations are empty, and if so, leaves the loop.
				boolean allStationsEmpty=true;
				for (Station<Bike> s:controlcenter.getStations()){
					if (!s.getVehicles().isEmpty()){
						allStationsEmpty=false;
						break;
					}
				}
				if (allStationsEmpty) {
					break;
				}

				// Creation of a station chosen randomly amongst the stations in the control center
				Station<Bike> randStation = new Station<Bike>(1,1);
				while (randStation.getVehicles().isEmpty()){
					randStation = controlcenter.getStations().get(rand.nextInt(controlcenter.getStations().size()));
				}				

				// Choice of a rentable vehicle to rent, and rents the vehicle
				// Checks the presence of a rentable vehicle inside of the station (because the station might be not empty and none of its vehicle can be rented)
				boolean presenceOfRentable=false;
				for (Bike b:randStation.getVehicles()){
					if (b.getState() instanceof IsOkState){
						presenceOfRentable=true;
						break;
					}
				}
				if (!presenceOfRentable){
					break;
				}
				Bike randBike = bfact.createVehicle();
				randBike.setState(new BrokenState(randBike));
				// Since there is at least one rentable Vehicle, keeps looking for it until it is found
				while (!(randBike.getState() instanceof IsOkState)){
					randBike = randStation.getVehicles().get(rand.nextInt(randStation.getVehicles().size()));
				}
				randStation.popVehicle(randBike, true);
				rentedBikes.add(randBike);
			}
			// Tries giving back a vehicle nbGiveBacks times
			for (int i=0;i<nbGiveBacks;i++){
				// Creation of a station chosen randomly amongst the stations in the control center
				Station<Bike> randStation = controlcenter.getStations().get(rand.nextInt(controlcenter.getStations().size()));
				while (randStation.getVehicles().size()==randStation.getCapacity()){
					randStation = controlcenter.getStations().get(rand.nextInt(controlcenter.getStations().size()));
				}

				//Choice if possible of a vehicle to give back, and tries giving back the vehicle
				if (rentedBikes.isEmpty()){
					break;
				}
				Bike randBike = rentedBikes.get(rand.nextInt(rentedBikes.size()));
				try {
					rentedBikes.remove(randBike);
					randStation.putVehicle(randBike, true);
				}
				catch (StationIsFullException e){
				}
			}
		}
	}

}
