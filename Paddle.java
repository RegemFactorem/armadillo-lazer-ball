
public class Paddle extends GameObject {

    // Add any state variables here
    private int yVelocity = 0;

    /**
     * Fill in this method with code that describes the behavior
     * of a paddle from one moment to the next
     */

    public void act() {
        setY(getY() + yVelocity);
        yVelocity = 0;

    }
    public int getYVelocity()
    {
        return yVelocity;
    }
    // Add any additional methods here
    public void setYVelocity(int yVelocityIn)
    {
        yVelocity = yVelocityIn;
    }
}
