/*classe à terminer quand les classes State seront definies */
package rentalsystem.worker;
import rentalsystem.Vehicle;
import rentalsystem.states.vehiclestates.RepairingState;

/**
 * Class representing repairers, in charge of repairing broken vehicles
 * @author nicolas.han.etu
 *
 */
public class Repairer extends Worker {
	/**
	 * Constructor of the class
	 * @param name The name of the repairer
	 */
    public Repairer (String name){
        super(name);
    }

    /**
     * Sets the given vehicle to a RepairingState
     * @param v The vehicle to repair
     */
    @Override
    public void effect (Vehicle v) {
        v.setState(new RepairingState(v));
    }
    
    
}
