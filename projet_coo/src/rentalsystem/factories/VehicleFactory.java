package rentalsystem.factories;
import rentalsystem.Vehicle;

/**
 * Class for a Vehicle Factory
 */
public abstract class VehicleFactory {

    /**
     * Attribute of the factory
     */
    protected int maxId; // saves the maximum id to give to a new Vehicle, and is incremented each time a vehicle is created, which guarantees the unicity of the id

    /**
     * Constructor of the class
     */
    public VehicleFactory(){
        this.maxId=1;
    }

    /**
     * Creates a new Vehicle with an unused id
     * @return The vehicle created by the factory
     */
    public abstract Vehicle createVehicle();
}
