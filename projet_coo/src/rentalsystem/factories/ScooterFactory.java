package rentalsystem.factories;
import rentalsystem.*;

/**
 * Class for a scooter factory
 */
public class ScooterFactory extends VehicleFactory{

    /**
     * Constructor for the class
     */
    public ScooterFactory(){
        super();
    }

    /**
     * Creates a new Scooter with an unused id
     * @return The Scooter created by the factory
     */
    @Override  
    public Scooter createVehicle(){
        return new Scooter(this.maxId++);
    }
}