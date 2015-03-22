import java.awt.*;

public class LazerBall extends Game {
    //team one
    private Ball ballOne;
    private Bullet bulletOne;
    private Defender defOneL;
    private Defender defOneR;


    //team two
    private Ball ballTwo;
    private Bullet bulletTwo;
    private Ball defTwoL;
    private Ball defTwoR;

    //boundaries
    private Paddle wallL;
    private Paddle wallR;
    private Paddle ceiling;
    private Paddle floor;

    private boolean bulletInMotion = false;

    private int defenderScore = 0;

    public void setup() {
        /*********
         * Team 1*
         *********/
        //Ball 2 characteristics
        ballOne = new Ball();
        ballOne.setSize(10,10);
        ballOne.setX(getFieldWidth()/2-10);
        ballOne.setY(getFieldHeight()/2);
        add(ballOne);

        //Bullet 2 characteristics
        bulletOne = new Bullet();
        bulletOne.setX(ballOne.getX());
        bulletOne.setY(ballOne.getY());

        //Defender 1 L characteristics
        defOneL = new Defender();
        defOneL.setSize(100,100);
        defOneL.setX(40);
        defOneL.setY(getFieldHeight()/6);
        defOneL.setColor(Color.green);
        add(defOneL);

        //Defender 1 R characteristics
        defOneR = new Defender();
        defOneR.setSize(100,100);
        defOneR.setX(40);
        defOneR.setY(getFieldHeight()*2/3);
        defOneR.setColor(Color.green);
        add(defOneR);

        /*********
         * Team 2*
         *********/
        //Ball 2 characteristics
        ballTwo = new Ball();
        ballTwo.setSize(10,10);
        ballTwo.setX(getFieldWidth()/2+10);
        ballTwo.setY(getFieldHeight()/2);
        add(ballTwo);

        //Bullet 2 characteristics
        bulletTwo = new Bullet();
        bulletTwo.setX(ballTwo.getX());
        bulletTwo.setY(ballTwo.getY());

        /*************
         * Boundaries*
         *************/
        //Left bounds
        wallL = new Paddle();
        wallL.setSize(5,getFieldHeight());
        wallL.setX(0);
        wallL.setY(0);
        add(wallL);

        //Right bounds
        wallR = new Paddle();
        wallR.setSize(5,getFieldHeight());
        wallR.setX(getFieldWidth()-5);
        wallR.setY(0);
        add(wallR);

        //Lower bounds
        ceiling = new Paddle();
        ceiling.setSize(getFieldWidth(),5);
        ceiling.setX(0);
        ceiling.setY(0);
        add(ceiling);

        //Top bounds
        floor = new Paddle();
        floor.setSize(getFieldWidth(),5);
        floor.setX(0);
        floor.setY(getFieldHeight() -5);
        add(floor);
        ballTwo.setColor(Color.white);


    }

    public void act() {
        setDelay(10); //Testing

        /****************
         * Key Detection*
         ****************/
        if(UpKeyPressed()) {
            System.out.println("UP ARROW");
        }

        //Controls for Defender 1 L: I,J,K,L
        if(IKeyPressed())
            defOneL.setYVelocity(-2);
        if(KKeyPressed())
            defOneL.setYVelocity(2);
        if(JKeyPressed())
            defOneL.setXVelocity(-2);
        if(LKeyPressed())
            defOneL.setXVelocity(2);

        //Shooting Bullet 2: 1,2,3,4
        if(OneKeyPressed()){
            bulletTwo.setColor(Color.green);
            bulletTwo.setSize(20,5);
            add(bulletTwo);
            bulletTwo.shootLeft();
            bulletInMotion = true;
        }
        if(TwoKeyPressed()){
            bulletTwo.setColor(Color.green);
            bulletTwo.setSize(20,5);
            add(bulletTwo);
            bulletTwo.shootRight();
            bulletInMotion = true;
        }
        if(ThreeKeyPressed()){
            bulletTwo.setColor(Color.green);
            bulletTwo.setSize(5,20);
            add(bulletTwo);
            bulletTwo.shootUp();
            bulletInMotion = true;
        }
        if(FourKeyPressed()){
            bulletTwo.setColor(Color.green);
            bulletTwo.setSize(5,20);
            add(bulletTwo);
            bulletTwo.shootDown();
            bulletInMotion = true;
        }

        //Ball 2 movement with bullet not shot: W,A,S,D
        if(WKeyPressed()&&!bulletInMotion){
            ballTwo.setYVelocity(-5);
            bulletTwo.keepBulletWithBall(ballTwo);
        }
        if(SKeyPressed()&&!bulletInMotion){
            ballTwo.setYVelocity(5);
            bulletTwo.keepBulletWithBall(ballTwo);
        }
        if(AKeyPressed()&&!bulletInMotion){
            ballTwo.setXVelocity(-5);
            bulletTwo.keepBulletWithBall(ballTwo);
        }
        if(DKeyPressed()&&!bulletInMotion){
            ballTwo.setXVelocity(5);
            bulletTwo.keepBulletWithBall(ballTwo);
        }

        //Ball 2 movement when bullet shot: W,A,S,D
        if(WKeyPressed() && bulletInMotion){
            ballTwo.setYVelocity(-5);
        }
        if(SKeyPressed()&& bulletInMotion){
            ballTwo.setYVelocity(5);
        }
        if(AKeyPressed()&& bulletInMotion){
            ballTwo.setXVelocity(-5);
        }
        if(DKeyPressed()&& bulletInMotion){
            ballTwo.setXVelocity(5);
        }



        /************
         *Boundaries*
         ************/
        //Sets boundaries for Defender 1 L
        if(defOneL.collides(wallL))
        {
            defOneL.setX(40);
            defOneL.setY(getFieldHeight()/6);
        }
        if(defOneL.collides(wallR))
        {
            defOneL.setX(40);
            defOneL.setY(getFieldHeight()/6);
        }
        if(defOneL.collides(ceiling))
        {
            defOneL.setX(40);
            defOneL.setY(getFieldHeight()/6);
        }
        if(defOneL.collides(floor))
        {
            defOneL.setX(40);
            defOneL.setY(getFieldHeight()/6);
        }

        //Sets boundaries for Defender 1 R
        if(defOneR.collides(wallL))
        {
            defOneR.setX(40);
            defOneR.setY(getFieldHeight() / 6);
        }
        if(defOneR.collides(wallR))
        {
            defOneR.setX(40);
            defOneR.setY(getFieldHeight()/6);
        }
        if(defOneR.collides(ceiling))
        {
            defOneR.setX(40);
            defOneR.setY(getFieldHeight()/6);
        }
        if(defOneR.collides(floor))
        {
            defOneR.setX(40);
            defOneR.setY(getFieldHeight()/6);
        }

        //Sets boundiares for ball 2
        if(ballTwo.collides(wallL)){
            ballTwo.setX(5);
        }
        if(ballTwo.collides(wallR)){
            ballTwo.setX(wallR.getX() -10);
        }
        if(ballTwo.collides(ceiling)){
            ballTwo.setY(5);
        }
        if(ballTwo.collides(floor)){
            ballTwo.setY(floor.getY() - 10);
        }

        //Sets boundaries for bullet 2
        if(bulletTwo.collides(wallL)){
            bulletHit();
            bulletInMotion = false;
            bulletTwo.setColor(Color.black);
            bulletTwo.keepBulletWithBall(ballTwo);
            bulletTwo.stopShooting();


        }
        if(bulletTwo.collides(wallR)){
            bulletHit();
            bulletInMotion = false;
            bulletTwo.setColor(Color.black);
            bulletTwo.keepBulletWithBall(ballTwo);
            bulletTwo.stopShooting();

        }
        if(bulletTwo.collides(ceiling)){
            bulletHit();
            bulletInMotion = false;
            bulletTwo.setColor(Color.black);
            bulletTwo.keepBulletWithBall(ballTwo);
            bulletTwo.stopShooting();

        }
        if(bulletTwo.collides(floor)){
            bulletHit();
            bulletInMotion = false;
            bulletTwo.setColor(Color.black);
            bulletTwo.keepBulletWithBall(ballTwo);
            bulletTwo.stopShooting();

        }


        /***********************************************
         * Collisions between player controlled objects*
         ***********************************************/
        //Bullet 2 & Defender 1 L collision and health lowering
        if(bulletTwo.collides(defOneL) && bulletInMotion)
        {
            bulletHit();
            bulletInMotion = false;
            defOneL.doDamage();
            bulletTwo.bulletCollision(ballTwo);
        }
        //Bullet 2 & Defender 1 R collision and health lowering
        if(bulletTwo.collides(defOneR) && bulletInMotion)
        {
            bulletHit();
            bulletInMotion = false;
            defOneR.doDamage();
            bulletTwo.bulletCollision(ballTwo);
        }

        //Ball 2 & Defender 1 L, Updates Score, Respawns ball
        if(ballTwo.collides(defOneL)){
            defOneL.setX(40);
            defOneL.setY(getFieldHeight()/6);
            ballTwo.setX(getFieldWidth() /2+10);
            ballTwo.setY(getFieldHeight() / 2);
            defenderScore++;
        }

        //Ball 2 & Defender 1 R, Updates Score, Respawns ball
        if(ballTwo.collides(defOneR)){
            defOneR.setX(40);
            defOneR.setY(getFieldHeight()*2/3);
            ballTwo.setX(getFieldWidth() /2+10);
            ballTwo.setY(getFieldHeight() / 2);
            defenderScore++;
        }


        /*************************
         * End of Game Situations*
         *************************/
        //Defender 1 L dies
        if(defOneL.getWidth() < 10)
        {
            stopGame();
            p2Wins();
        }
        if(defOneL.getWidth() <= 50)
            defOneL.setColor(Color.red);

        //Defender 1 R dies
        if(defOneR.getWidth() < 10)
        {
            stopGame();
            p2Wins();
        }
        if(defOneR.getWidth() <= 50)
            defOneR.setColor(Color.red);

        //Ball 2 dies too many times
        if(defenderScore == 99)
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
