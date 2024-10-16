package rentalsystem.worker;

import rentalsystem.Vehicle;

/**
 * Class representing any kind of worker related to the vehicle stations management
 * @author nicolas.han.etu
 *
 */
public abstract class Worker {

    /**
     * The name of the worker
     */
    protected String name;

    /**
     * Constructor of the class
     * @param name The name of the worker
     */
    public Worker (String name){
        this.name = name;
    }

    /**
     * The effect of the Worker
     * @param v The vehicle to which the effect is applied
     */
    public abstract void effect (Vehicle v);
    
    /**
     * Makes the worker visit the given vehicle
     * @param v The vehicle to visit
     */
    public void visit(Vehicle v) {
    	v.accept(this);
    }
}