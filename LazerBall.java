import java.awt.*;

public class LazerBall extends Game {
  private Ball ballL;
  private Ball bulletL;
  private Ball ballDef;
  private Paddle paddleL;
  private Paddle paddleR;
  private Paddle ceiling;
  private Paddle floor;
  private boolean bulletInMotion = false;

  private int ballScore = 0;

  public void setup() {
    ballDef = new Ball();   //Defender charactercits
    ballDef.setSize(100,100);
    ballDef.setX(40);
    ballDef.setY(40);
    ballDef.setColor(Color.green);
    add(ballDef);

    ballL = new Ball();    //Ball characteristics
    ballL.setSize(10,10);
    ballL.setX(getFieldWidth()/2);
    ballL.setY(getFieldHeight()/2);
    add(ballL);
    bulletL = new Ball();
    bulletL.setSize(20,5);
    bulletL.setX(ballL.getX());
    bulletL.setY(ballL.getY());


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
    ballL.setColor(Color.white);


  }

  /**dwlk
    * Fill in this method with code that tells the playing field what to do
    * from one moment to the next
    */
  public void act() {
    setDelay(10); //Testing

    if(UpKeyPressed()) {
        System.out.println("UP ARROW");
    }
    if(OneKeyPressed()){
      bulletL.setColor(Color.green);
      add(bulletL);
      bulletInMotion = true;
      bulletL.setXVelocity(10);
      bulletL.setYVelocity(0);
    }
    if(WKeyPressed()&&!bulletInMotion){        //Ball movement with bullet not shot W,A,S,D
      ballL.setYVelocity(-5);
      bulletL.setX(ballL.getX());
      bulletL.setY(ballL.getY());
    }
    if(SKeyPressed()&&!bulletInMotion){
      ballL.setYVelocity(5);
      bulletL.setX(ballL.getX());
      bulletL.setY(ballL.getY());
    }
    if(AKeyPressed()&&!bulletInMotion){
      ballL.setXVelocity(-5);
      bulletL.setX(ballL.getX());
      bulletL.setY(ballL.getY());
    }
    if(DKeyPressed()&&!bulletInMotion){
      ballL.setXVelocity(5);
      bulletL.setX(ballL.getX());
      bulletL.setY(ballL.getY());
    }

    if(WKeyPressed()&& bulletInMotion){        //Ball movement when bullet shot W,A,S,D
      ballL.setYVelocity(-5);
    }
    if(SKeyPressed()&& bulletInMotion){
      ballL.setYVelocity(5);
    }
    if(AKeyPressed()&& bulletInMotion){
      ballL.setXVelocity(-5);
    }
    if(DKeyPressed()&& bulletInMotion){
      ballL.setXVelocity(5);
    }


    if(ballL.collides(paddleL)){      //Sets boundiares for ball
      ballL.setX(5);
    }
    if(ballL.collides(paddleR)){
      ballL.setX(paddleR.getX() -10);
    }
    if(ballL.collides(ceiling)){
      ballL.setY(5);
    }
    if(ballL.collides(floor)){
      ballL.setY(floor.getY() - 10);
    }

    if(bulletL.collides(paddleL)){
      bulletL.setColor(Color.black);   //Sets boundiares for bullet
      bulletL.setX(ballL.getX());
      bulletL.setY(ballL.getY());
      bulletL.setXVelocity(0);
      bulletL.setYVelocity(0);
      bulletInMotion = false;
      BulletHit();
    }
    if(bulletL.collides(paddleR)){
      bulletL.setColor(Color.black);
      bulletL.setX(ballL.getX());
      bulletL.setY(ballL.getY());
      bulletL.setXVelocity(0);
      bulletL.setYVelocity(0);
      bulletInMotion = false;
      BulletHit();
    }
    if(bulletL.collides(ceiling)){
      bulletL.setColor(Color.black);
      bulletL.setX(ballL.getX());
      bulletL.setY(ballL.getY());
      bulletL.setXVelocity(0);
      bulletL.setYVelocity(0);
      bulletInMotion = false;
      BulletHit();
    }
    if(bulletL.collides(floor)){
      bulletL.setColor(Color.black);
      bulletL.setX(ballL.getX());
      bulletL.setY(ballL.getY());
      bulletL.setXVelocity(0);
      bulletL.setYVelocity(0);
      bulletInMotion = false;
      BulletHit();
    }

    if(ballDef.collides(paddleL))          //Sets boundiares for Defender
      ballDef.setX(5);
    if(ballDef.collides(paddleR))
      ballDef.setX(paddleR.getX() - ballDef.getWidth());
    if(ballDef.collides(ceiling))
      ballDef.setY(5);
    if(ballDef.collides(floor))
      ballDef.setY(floor.getY() - ballDef.getHeight());

    if(IKeyPressed())                      //Controls for Defender I,J,K,L
      ballDef.setYVelocity(-2);
    if(KKeyPressed())
      ballDef.setYVelocity(2);
    if(JKeyPressed())
      ballDef.setXVelocity(-2);
    if(LKeyPressed())
      ballDef.setXVelocity(2);



    if(ballL.collides(ballDef)){
      ballDef.setX(10);
      ballDef.setY(10);                        //Dectects Collision With Defender, Updates Score, Respawns ball
      ballL.setX(getFieldWidth() /2);
      ballL.setY(getFieldHeight() / 2);
      ballScore++;
    }


    if(bulletL.collides(ballDef) && bulletInMotion == true)
    {
      ballDef.setSize(ballDef.getWidth() - 10, ballDef.getHeight() - 10);
      System.out.println("Bullet hit 1def");
      bulletL.setX(ballL.getX());
      bulletL.setY(ballL.getY());
      bulletL.setXVelocity(0);
      bulletL.setYVelocity(0);
      bulletInMotion = false;
      BulletHit();
      //ballDef.doDamage();
      bulletL.setColor(Color.black);
    }
    if(ballDef.getWidth() < 10)
    {
      stopGame();
        p2Wins();
    }
      if(ballDef.getWidth() <= 50)
        ballDef.setColor(Color.red);


    //if(ballDef.getHealth() == -20){
     // stopGame();
    //p2Wins();
  //}

    if(ballScore == 99)
    {
      stopGame();
      p1Wins();
    }

  }

  public static void main(String[] args) {
    LazerBall lb = new LazerBall();
    lb.setVisible(true);
    lb.initComponents();
  }
}
