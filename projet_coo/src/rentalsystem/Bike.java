package rentalsystem;

/**
 * Bike class
 * @author nicolas.han.etu
 *
 */
public class Bike extends Vehicle {
	
	/**
	 * Bike class constructor
	 * @param id The id of the bike
	 */
	public Bike(int id) {
		super(id);
	}

	@Override
	public String toString(){
		return "Bike "+this.id;
	}
}
