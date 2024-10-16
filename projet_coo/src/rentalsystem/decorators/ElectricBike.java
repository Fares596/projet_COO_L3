package rentalsystem.decorators;
import rentalsystem.Bike;

/**
 * Decorator for an Electric Bike
 * @author nicolas.han.etu
 *
 */
public class ElectricBike extends BikeDecorator{
	/**
	 * Constructor of the class
	 * @param bike The bike this decorates
	 */
    public ElectricBike(Bike bike){
        super(bike);
    }

    /**
     * toString method for the ElectricBike
     * @return A string representing an ElectricBike
     */
    public String toString(){
        return "Electric "+this.bike.toString();
    }
}
