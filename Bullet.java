import java.awt.*;

public class Bullet extends Ball {

    public void bulletCollision(Ball ball){

        keepBulletWithBall(ball);
        stopShooting();
        setColor(Color.black);
    }
    public void keepBulletWithBall(Ball ball)
    {
        setX(ball.getX());
        setY(ball.getY());
    }
    public void stopShooting()
    {
        setXVelocity(0);
        setYVelocity(0);
    }
    public void shootRight()
    {
        setXVelocity(10);
        setYVelocity(0);
    }
    public void shootLeft()
    {
        setXVelocity(-10);
        setYVelocity(0);
    }
    public void shootUp()
    {
        setXVelocity(0);
        setYVelocity(-10);
    }
    public void shootDown()
    {
        setXVelocity(0);
        setYVelocity(10);
    }

}
