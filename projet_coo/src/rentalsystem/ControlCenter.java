package rentalsystem;
import rentalsystem.rearrangements.*;
import java.util.List;
import rentalsystem.exceptions.*;
import java.util.Random;
import rentalsystem.states.vehiclestates.RepairingState;

/**
 * Control center class
 * @author nicolas.han.etu
 *
 */
public class ControlCenter<T extends Vehicle>{
	
	private List<Station<T>> stations;
	private RearrangementMethod<T> method;
	private Interval interval;
	private Random random;
	
	/**
	 * Control center class contructor
	 * @param stations The list of stations the control center manages
	 * @param method The rearrangement method
	 */
	 public ControlCenter(List<Station<T>> stations, RearrangementMethod<T> method) {
		this.stations=stations;
		this.method=method;
		this.random = new Random();
		this.interval = new Interval(this.random.nextInt(Interval.NB_MAX_ACTIONS-1)+1);
	}
	
	/**
	 * Accesses the RearrangementMethod used by the ControlCenter
	 * @return The RearrangementMethod object
	 */
	public RearrangementMethod<T> getMethod() {
		return this.method;
	}
	
	/**
	 * Accesses the current time interval
	 * @return The current time interval (object Interval)
	 */
	public Interval getInterval() {
		return this.interval;
	}
	
	/**
	 * Accesses the stations managed by the control center
	 * @return The list of stations managed by the control center
	 */
	public List<Station<T>> getStations() {
		return this.stations;
	}
	
	/**
	 * Sets the list of stations of the control center to a new list
	 * @param newStations The list of stations for the control center to manage
	 */
	public void setStations(List<Station<T>> newStations) {
		this.stations=newStations;
	}
	
	/**
	 * Sets a new RearrangementMethod to the ControlCenter
	 * @param newMethod The new RearrangementMethod
	 */
	public void setMethod(RearrangementMethod<T> newMethod) {
		this.method=newMethod;
	}
	
	/**
	 * Adds a station to the list of stations of the control center
	 * @param newStation The stations to add
	 */
	public void addStation(Station<T> newStation) {
		this.stations.add(newStation);
	}
	
	/**
	 * Removes the station from the list of stations if able to
	 * @param station The station to remove
	 * @throws StationNotManagedException Exception raised if the station given is not in the list of stations
	 */
	public void removeStation(Station<T> station) throws StationNotManagedException{
		if (!(this.stations.contains(station))) {
			throw new StationNotManagedException("The given station is not managed by the control center");
		}
		this.stations.remove(station);
	}
	

	/**
	 * Checks if a rearrangement of the vehicles is needed, and if so, rearranges the stations
	 * @throws VehicleNotInStationException Exception raised if a vehicle to remove is not in the station (should not happen: cf rearrange() method doc)
	 */
	public void checkForRearrangements() throws VehicleNotInStationException {
		for (Station<T> s : this.stations){
			if (s.getState().getNbIntervalsInState()>=2){
				this.rearrange();
				return;
			}
		}
	}
	
	/**
	 * Rearranges the vehicles of the stations to make a more balanced repartition
	 * @throws VehicleNotInStationException Exception raised if a vehicle to remove is not in the station (should not happen because the code doesn't try to remove a vehicle that isn't in the station or from an empty station)
	 */
	public void rearrange() throws VehicleNotInStationException {
		System.out.println("Rearrangement needed");
		this.method.rearrange(this.stations);
	}
	
	
	/**
	 * Creates a new Interval, used when the previous one is over
	 * @param n The number of actions (rentals/returns) that can be done during the interval
	 */
	public void createInterval(int n) {
		this.interval = new Interval(n);
	}
	
	/**
	 * Manages time ticking out
	 * @throws VehicleNotInStationException Should not happen (cf rearrange method documentation)
	 */
	public void decreaseInterval() throws VehicleNotInStationException {
		this.interval.decreaseActions();
		if (this.interval.getNbActionsLeft()<=0) {
			System.out.println("\n--- New time interval ---\n");
			this.createInterval(this.random.nextInt(Interval.NB_MAX_ACTIONS-1)+1);
			for (Station<T> s:this.stations) {
				s.getState().increaseNbIntervals();
				s.checkLoneliness();
				for (Vehicle v:s.getVehicles()) {
					if (v.getState() instanceof RepairingState){
					}
					v.getState().repair();
				}
			}
			this.checkForRearrangements();
			
		}
	}
	
	/**
	 * Updates the number of actions left every time the control center is notified
	 * @param isRented true if the method is called when a vehicle is rented, false otherwise
	 * @param vehicle The vehicle relevant to the notification
	 * @param station The station that notified the control center
	 * @throws VehicleNotInStationException Should not happen (cf rearrange method documentation)
	 */
	public void update(boolean isRented, Vehicle vehicle, Station<T> station) throws VehicleNotInStationException {
		if (isRented) {
			System.out.println("The vehicle "+vehicle+" was rented from "+station);
		}
		else {
			System.out.println("The vehicle "+vehicle+" was given back to "+station);
		}
		this.decreaseInterval();
	}
	
	/**
	 * toString method for ControlCenter class
	 * @return A String representing the control center
	 */
	public String toString() {
		return "Control Center";
	}	
}
