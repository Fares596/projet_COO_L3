package rentalsystem.factories;
import rentalsystem.*;

/**
 * Class for a bike factory
 */
public class BikeFactory extends VehicleFactory{

    /**
     * Constructor for the class
     */
    public BikeFactory(){
        super();
    }

    /**
     * Creates a new Bike with an unused id
     * @return The Bike created by the factory
     */
    @Override  
    public Bike createVehicle(){
        return new Bike(this.maxId++);
    }
}
