/**
 * Abstract class defining the a shootable target. Booleans begin as false.
 * After a shot is received, wasHit/wasMissed are set. value() returns the
 * of points the user received for this target. It may be positive, 
 * negative, or zero. 
 * 
 * @author Bradley Allen
 * 
 */
abstract class Target {
    protected boolean wasHit;
    protected boolean wasMissed;
    
    /**
     * Constructor. Initializes booleans to false.
     */
    public Target() {
        wasHit = wasMissed = false;
    }

    /**
     * When the user fires or passses, this method is to be called.
     * 
     * @param projectile what the user entered
     * @return a text response to the projectile.
     */
    public abstract String receiveShot(String projectile);

    /**
     * Value of this target. Begins as 0. Once the target has been shot, the
     * value is set accordingly.
     * 
     * @return the points earned/deducted by this target.
     */
    public abstract int value();
}