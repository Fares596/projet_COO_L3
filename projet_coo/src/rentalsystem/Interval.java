package rentalsystem;

/**
 * Class for time intervals, represented by a number of actions that can be done during this interval
 * @author nicolas.han.etu
 *
 */
public class Interval {
	private int nbActionsLeft;
	/**
	 * The maximum number of actions an interval can represent
	 */
	public static final int NB_MAX_ACTIONS=10;
	
	/**
	 * Constructor for the class
	 * @param nbActions The number of actions that can be done during be done during the interval
	 */
	public Interval(int nbActions) {
		this.nbActionsLeft=nbActions;
	}
	
	/**
	 * Accesses the number of actions left before the interval is over
	 * @return The number of actions left
	 */
	public int getNbActionsLeft() {
		return this.nbActionsLeft;
	}
	
	/**
	 * Sets a new value for the number of actions
	 * @param newValue The new number of actions
	 */
	public void setNbActionsLeft(int newValue) {
		this.nbActionsLeft=newValue;
	}
	
	/**
	 * Decreases the number of actions left
	 */
	public void decreaseActions() {
		this.nbActionsLeft--;
	}
}
