package rentalsystem.decorators;
import rentalsystem.Bike;

/**
 * Decorator for a Bike with a Pannier Rack
 * @author nicolas.han.etu
 *
 */
public class PannierRackBike extends BikeDecorator {
	
	/**
	 * Constructor of the class
	 * @param bike The bike this decorates
	 */
    public PannierRackBike(Bike bike){
        super(bike);
    }

    /**
     * toString for a PannierRackBike
     * @return A String representing the class
     */
    public String toString(){
        return this.bike.toString() + " with pannier rack";
    }
}
