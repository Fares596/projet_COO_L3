package rentalsystem.decorators;
import rentalsystem.Bike;

/**
 * Decorator for Bikes with a Basket
 * @author nicolas.han.etu
 *
 */
public class BasketBike extends BikeDecorator{
	/**
	 * Constructor of the class
	 * @param bike The bike this decorates
	 */
    public BasketBike(Bike bike){
        super(bike);
    }

    /**
     * toString method for BasketBike
     */
    public String toString(){
        return this.bike.toString() + " with basket";
    }
}
