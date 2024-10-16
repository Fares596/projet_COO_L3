package rentalsystem.decorators;
import rentalsystem.Bike;

/**
 * Decorator for a Bike
 * @author nicolas.han.etu
 *
 */
public abstract class BikeDecorator extends Bike {
    /**
     * The bike decorated by the decorator
     */
    protected Bike bike;
    
    /**
     * Constructor of the class
     * @param bike The bike this decorates
     */
    public BikeDecorator(Bike bike){
        super(bike.getId());
        this.bike=bike;
    }

    /**
     * toString method for a Bike Decorator
     * @return A String representing the decorator
     */
    public String toString(){
        return this.bike.toString();
    }
}
