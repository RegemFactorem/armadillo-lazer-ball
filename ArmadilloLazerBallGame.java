/*
 * This code is protected under the Gnu General Public License (Copyleft), 2005 by
 * IBM and the Computer Science Teachers of America organization. It may be freely
 * modified and redistributed under educational fair use.
 */

import java.awt.*;

public class Pong extends Game {
  private Ball ball;
  private Ball ballDef;

  private Paddle paddleL;
  private Paddle paddleR;
  private Paddle ceiling;
  private Paddle floor;

  private int ballScore = 0;

  public void setup() {
    ballDef = new Ball();   //Defender charactercits
    ballDef.setSize(100,100);
    ballDef.setX(40);
    ballDef.setY(40);
    ballDef.setColor(Color.red);
    add(ballDef);

    ball = new Ball();    //Ball characteristics
    ball.setSize(10,10);
    ball.setX(getFieldWidth()/2);
    ball.setY(getFieldHeight()/2);
    ball.setColor(Color.yellow);
    add(ball);

    paddleL = new Paddle();    //Reft bounds
    paddleL.setSize(5,600);
    paddleL.setX(0);
    paddleL.setY(0);
    add(paddleL);

    paddleR = new Paddle();      //Right bounds
    paddleR.setSize(5,600);
    paddleR.setX(getFieldWidth()-5);
    paddleR.setY(0);
    add(paddleR);

    ceiling = new Paddle();   //Lower bounds
    ceiling.setSize(600,5);
    ceiling.setX(0);
    ceiling.setY(0);
    add(ceiling);

    floor = new Paddle();     //Top bounds
    floor.setSize(600,5);
    floor.setX(0);
    floor.setY(getFieldHeight() -5);
    add(floor);


  }

  /**dwlk
    * Fill in this method with code that tells the playing field what to do
    * from one moment to the next
    */
  public void act() {
    setDelay(10); //Testing


    if(WKeyPressed())        //Ball movement W,A,S,D
      ball.setYVelocity(-5);
    if(SKeyPressed())
      ball.setYVelocity(5);
    if(AKeyPressed())
      ball.setXVelocity(-5);
    if(DKeyPressed())
      ball.setXVelocity(5);

    if(ball.collides(paddleL))      //Sets boundiares for ball
      ball.setX(5);
    if(ball.collides(paddleR))
      ball.setX(paddleR.getX() -10);
    if(ball.collides(ceiling))
      ball.setY(5);
    if(ball.collides(floor))
      ball.setY(floor.getY() - 10);

    if(ballDef.collides(paddleL))          //Sets boundiares for Defender
      ballDef.setX(5);
    if(ballDef.collides(paddleR))
      ballDef.setX(paddleR.getX() -100);
    if(ballDef.collides(ceiling))
      ballDef.setY(5);
    if(ballDef.collides(floor))
      ballDef.setY(floor.getY() - 100);


    if(IKeyPressed())                      //Controls for Defender I,J,K,L
      ballDef.setYVelocity(-2);
    if(KKeyPressed())
      ballDef.setYVelocity(2);
    if(JKeyPressed())
      ballDef.setXVelocity(-2);
    if(LKeyPressed())
      ballDef.setXVelocity(2);



    if(ball.collides(ballDef)){ //Dectects Collision With Defender, Updates Score, Respawns ball
      ballDef.setX(0);
      ballDef.setY(getFieldHeight()/2 - 50);
      ball.setX(getFieldWidth()-10);
      ball.setY(getFieldHeight() / 2);
      ballScore++;
    }

    // Add any additional methods here



    if(ballScore == 99)
    {
      stopGame();
      p1Wins();
    }

  }
  /**
   * This code has been provided for you, and should not be modified
   */
  public static void main(String[] args) {
    Pong p = new Pong();
    p.setVisible(true);
    p.initComponents();
  }
}
