package rentalsystem;

/**
 * Scooter class
 * @author nicolas.han.etu
 *
 */
public class Scooter extends Vehicle {
	
	/**
	 * Scooter class constructor
	 * @param id the id of the scooter
	 */
	public Scooter(int id) {
		super(id);
	}

	/**
	 * toString method for the Scooter class
	 * @return A String representing the scooter
	 */
	@Override
	public String toString(){
		return "Scooter";
	}
}
