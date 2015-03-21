
public class Ball extends GameObject {
 // Add any state variables here

private int xVelocity = 0;
private int yVelocity = 0;


 /**
  * Fill in this method with code that describes the behavior
  * of a ball from one moment to the next
  */
 public void act() {
setX(getX() + xVelocity);
setY(getY() + yVelocity);
setYVelocity(0);
setXVelocity(0);

 }

 public int getXVelocity()
 {
   return xVelocity;
 }
  public int getYVelocity()
 {
   return yVelocity;
 }
   public void setYVelocity(int vIn)
 {
   yVelocity = vIn;
 }
    public void setXVelocity(int xIn)
 {
   xVelocity = xIn;
 }
 // Add any additional methods here
}
