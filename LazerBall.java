import java.awt.*;

public class LazerBall extends Game {
    //team one
    private Ball ballOne;
    private Defender defOneL;
    private Defender defOneR;
    private Bullet bulletOne;

    //team two
    private Ball ballTwo;
    private Ball defTwoL;
    private Ball defTwoR;
    private Bullet bulletTwo;

    //boundaries
    private Paddle wallL;
    private Paddle wallR;
    private Paddle ceiling;
    private Paddle floor;

    private boolean bulletInMotion = false;

    private int ballScore = 0;

  public void setup() {

    defOneL = new Defender();   //Defender 1 L characteristics
    defOneL.setSize(100,100);
    defOneL.setX(40);
    defOneL.setY(getFieldHeight()/6);
    defOneL.setColor(Color.green);
    add(defOneL);

    defOneR = new Defender();   //Defender 1 R characteristics
    defOneR.setSize(100,100);
    defOneR.setX(40);
    defOneR.setY(getFieldHeight()*2/3);
    defOneR.setColor(Color.green);
    add(defOneR);

    ballOne = new Ball();    //Ball characteristics
    ballOne.setSize(10,10);
    ballOne.setX(getFieldWidth()/2-10);
    ballOne.setY(getFieldHeight()/2);
    add(ballOne);

    bulletOne = new Bullet();
    bulletOne.setX(ballOne.getX());
    bulletOne.setY(ballOne.getY());


    wallL = new Paddle();    //Reft bounds
    wallL.setSize(5,getFieldHeight());
    wallL.setX(0);
    wallL.setY(0);
    add(wallL);

    wallR = new Paddle();      //Right bounds
    wallR.setSize(5,getFieldHeight());
    wallR.setX(getFieldWidth()-5);
    wallR.setY(0);
    add(wallR);

    ceiling = new Paddle();   //Lower bounds
    ceiling.setSize(getFieldWidth(),5);
    ceiling.setX(0);
    ceiling.setY(0);
    add(ceiling);

    floor = new Paddle();     //Top bounds
    floor.setSize(getFieldWidth(),5);
    floor.setX(0);
    floor.setY(getFieldHeight() -5);
    add(floor);
    ballOne.setColor(Color.white);


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

                                            //Shooting Bullet 1,2,3,4
    if(OneKeyPressed()){
      bulletOne.setColor(Color.green);
      bulletOne.setSize(20,5);
      add(bulletOne);
      bulletOne.shootLeft();
      bulletInMotion = true;
    }
    if(TwoKeyPressed()){
       bulletOne.setColor(Color.green);
       bulletOne.setSize(20,5);
       add(bulletOne);
       bulletOne.shootRight();
       bulletInMotion = true;
    }
      if(ThreeKeyPressed()){
          bulletOne.setColor(Color.green);
          bulletOne.setSize(5,20);
          add(bulletOne);
          bulletOne.shootUp();
          bulletInMotion = true;
      }
      if(FourKeyPressed()){
          bulletOne.setColor(Color.green);
          bulletOne.setSize(5,20);
          add(bulletOne);
          bulletOne.shootDown();
          bulletInMotion = true;
      }


    if(WKeyPressed()&&!bulletInMotion){        //Ball movement with bullet not shot W,A,S,D
      ballOne.setYVelocity(-5);
      bulletOne.keepBulletWithBall(ballOne);
    }
    if(SKeyPressed()&&!bulletInMotion){
      ballOne.setYVelocity(5);
      bulletOne.keepBulletWithBall(ballOne);
    }
    if(AKeyPressed()&&!bulletInMotion){
      ballOne.setXVelocity(-5);
        bulletOne.keepBulletWithBall(ballOne);
    }
    if(DKeyPressed()&&!bulletInMotion){
      ballOne.setXVelocity(5);
        bulletOne.keepBulletWithBall(ballOne);
    }


    if(WKeyPressed() && bulletInMotion){        //Ball movement when bullet shot W,A,S,D
      ballOne.setYVelocity(-5);
    }
    if(SKeyPressed()&& bulletInMotion){
      ballOne.setYVelocity(5);
    }
    if(AKeyPressed()&& bulletInMotion){
      ballOne.setXVelocity(-5);
    }
    if(DKeyPressed()&& bulletInMotion){
      ballOne.setXVelocity(5);
    }

                                        //Sets boundiares for ball
    if(ballOne.collides(wallL)){
      ballOne.setX(5);
    }
    if(ballOne.collides(wallR)){
      ballOne.setX(wallR.getX() -10);
    }
    if(ballOne.collides(ceiling)){
      ballOne.setY(5);
    }
    if(ballOne.collides(floor)){
      ballOne.setY(floor.getY() - 10);
    }
                                        //Sets boundiares for bullet
    if(bulletOne.collides(wallL)){
        bulletHit();
        bulletInMotion = false;
        bulletOne.setColor(Color.black);
        bulletOne.keepBulletWithBall(ballOne);
      bulletOne.stopShooting();


    }
    if(bulletOne.collides(wallR)){
        bulletHit();
        bulletInMotion = false;
        bulletOne.setColor(Color.black);
      bulletOne.keepBulletWithBall(ballOne);
        bulletOne.stopShooting();

    }
    if(bulletOne.collides(ceiling)){
        bulletHit();
        bulletInMotion = false;
      bulletOne.setColor(Color.black);
      bulletOne.keepBulletWithBall(ballOne);
        bulletOne.stopShooting();

    }
    if(bulletOne.collides(floor)){
        bulletHit();
        bulletInMotion = false;
      bulletOne.setColor(Color.black);
      bulletOne.keepBulletWithBall(ballOne);
        bulletOne.stopShooting();

    }

                                                                   //bullet defender 1 L collision and health lowering
      if(bulletOne.collides(defOneL) && bulletInMotion == true)
      {
          bulletHit();
          bulletInMotion = false;
          defOneL.doDamage();
          System.out.println("Bullet hit 1def");
          bulletOne.keepBulletWithBall(ballOne);
          bulletOne.stopShooting();
          bulletOne.setColor(Color.black);
      }
                                                                   //bullet defender 1 R collision and health lowering
      if(bulletOne.collides(defOneR) && bulletInMotion == true)
      {
          bulletHit();
          bulletInMotion = false;
          defOneR.doDamage();
          System.out.println("Bullet hit 1def");
          bulletOne.keepBulletWithBall(ballOne);
          bulletOne.stopShooting();
          bulletOne.setColor(Color.black);
      }

    if(defOneL.collides(wallL))          //Sets boundiares for Defender
      defOneL.setX(5);
    if(defOneL.collides(wallR))
      defOneL.setX(wallR.getX() - defOneL.getWidth());
    if(defOneL.collides(ceiling))
      defOneL.setY(5);
    if(defOneL.collides(floor))
      defOneL.setY(floor.getY() - defOneL.getHeight());

    if(IKeyPressed())                      //Controls for Defender I,J,K,L
      defOneL.setYVelocity(-2);
    if(KKeyPressed())
      defOneL.setYVelocity(2);
    if(JKeyPressed())
      defOneL.setXVelocity(-2);
    if(LKeyPressed())
      defOneL.setXVelocity(2);



    if(ballOne.collides(defOneL)){
      defOneL.setX(10);
      defOneL.setY(10);                        //Dectects Collision With Defender, Updates Score, Respawns ball
      ballOne.setX(getFieldWidth() /2);
      ballOne.setY(getFieldHeight() / 2);
      ballScore++;
    }



      if(defOneL.getWidth() < 10)
      {
          stopGame();
          p2Wins();
      }
      if(defOneL.getWidth() <= 50)
          defOneL.setColor(Color.red);

      if(defOneR.getWidth() < 10)
      {
          stopGame();
          p2Wins();
      }
      if(defOneR.getWidth() <= 50)
          defOneR.setColor(Color.red);


    //if(defOneL.getHealth() == -20){
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
