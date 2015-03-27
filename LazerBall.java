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
    private Defender defTwoL;
    private Defender defTwoR;

    //boundaries
    private Paddle wallL;
    private Paddle wallR;
    private Paddle ceiling;
    private Paddle floor;

    private boolean bullet1InMotion = false;
    private boolean bullet2InMotion = false;

    private int defenderScore = 0;

    public void setup() {
        /*********
         * Team 1*
         *********/
        //Ball 2 characteristics
        ballOne = new Ball();
        ballOne.setSize(10, 10);
        ballOne.setX(getFieldWidth() / 2 - 10);
        ballOne.setY(getFieldHeight() / 2);
        ballOne.setColor(Color.GREEN);
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
        ballTwo.setY(getFieldHeight() / 2);
        add(ballTwo);

        //Bullet 2 characteristics
        bulletTwo = new Bullet();
        bulletTwo.setX(ballTwo.getX());
        bulletTwo.setY(ballTwo.getY());

        //Defender 2 L characteristics
        defTwoL = new Defender();
        defTwoL.setSize(100,100);
        defTwoL.setX(getFieldWidth()-40);
        defTwoL.setY(getFieldHeight()/6);
        defTwoL.setColor(Color.green);
        add(defTwoL);

        //Defender 1 R characteristics
        defTwoR = new Defender();
        defTwoR.setSize(100,100);
        defTwoR.setX(getFieldWidth()-40);
        defTwoR.setY(getFieldHeight()*2/3);
        defTwoR.setColor(Color.green);
        add(defTwoR);
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

        //Ball 1 movement with bullet not shot: I,J,K,L
        if(WKeyPressed()&&!bullet1InMotion){
            ballOne.setYVelocity(-5);
            bulletOne.keepBulletWithBall(ballOne);
        }
        if(SKeyPressed()&&!bullet1InMotion){
            ballOne.setYVelocity(5);
            bulletOne.keepBulletWithBall(ballOne);
        }
        if (AKeyPressed()&&!bullet1InMotion){
            ballOne.setXVelocity(-5);
            bulletOne.keepBulletWithBall(ballOne);
        }
        if(DKeyPressed()&&!bullet1InMotion){
            ballOne.setXVelocity(5);
            bulletOne.keepBulletWithBall(ballOne);
        }

        //Ball 1 movement when bullet shot: I,J,K,L
        if(WKeyPressed() && bullet1InMotion){
            ballOne.setYVelocity(-5);
        }
        if(SKeyPressed()&& bullet1InMotion){
            ballOne.setYVelocity(5);
        }
        if(AKeyPressed()&& bullet1InMotion){
            ballOne.setXVelocity(-5);
        }
        if (DKeyPressed()&& bullet1InMotion){
            ballOne.setXVelocity(5);
        }

        //Shooting Bullet 1: Q,E
        if(QKeyPressed()){
            bulletOne.setColor(Color.green);
            bulletOne.setSize(20,5);
            add(bulletOne);
            bulletOne.shootLeft();
            bullet1InMotion = true;
        }
        if(EKeyPressed()){
            bulletOne.setColor(Color.green);
            bulletOne.setSize(20,5);
            add(bulletOne);
            bulletOne.shootRight();
            bullet1InMotion = true;
        }
        //Shooting Bullet 2: U,O
        if(UKeyPressed()){
            bulletTwo.setColor(Color.green);
            bulletTwo.setSize(20,5);
            add(bulletTwo);
            bulletTwo.shootLeft();
            bullet2InMotion = true;
        }
        if(OKeyPressed()){
            bulletTwo.setColor(Color.green);
            bulletTwo.setSize(20,5);
            add(bulletTwo);
            bulletTwo.shootRight();
            bullet2InMotion = true;
        }

        //Ball 2 movement with bullet not shot: W,A,S,D
        if(IKeyPressed()&&!bullet2InMotion){
            ballTwo.setYVelocity(-5);
            bulletTwo.keepBulletWithBall(ballTwo);
        }
        if(KKeyPressed()&&!bullet2InMotion){
            ballTwo.setYVelocity(5);
            bulletTwo.keepBulletWithBall(ballTwo);
        }
        if(JKeyPressed()&&!bullet2InMotion){
            ballTwo.setXVelocity(-5);
            bulletTwo.keepBulletWithBall(ballTwo);
        }
        if(LKeyPressed()&&!bullet1InMotion){
            ballTwo.setXVelocity(5);
            bulletTwo.keepBulletWithBall(ballTwo);
        }

        //Ball 2 movement when bullet shot: W,A,S,D
        if(IKeyPressed() && bullet2InMotion){
            ballTwo.setYVelocity(-5);
        }
        if(KKeyPressed()&& bullet1InMotion){
            ballTwo.setYVelocity(5);
        }
        if(JKeyPressed()&& bullet2InMotion){
            ballTwo.setXVelocity(-5);
        }
        if(LKeyPressed()&& bullet2InMotion){
            ballTwo.setXVelocity(5);
        }



        /************
         *Boundaries*
         ************/
        //Sets boundaries for Defender 1 L
        if(defOneL.collides(wallL))
        {
            defOneL.setX(40);
            defOneL.setY(getFieldHeight() / 6);
        }
        if(defOneL.collides(wallR))
        {
            defOneL.setX(40);
            defOneL.setY(getFieldHeight() / 6);
        }
        if(defOneL.collides(ceiling))
        {
            defOneL.setX(40);
            defOneL.setY(getFieldHeight() / 6);
        }
        if(defOneL.collides(floor))
        {
            defOneL.setX(40);
            defOneL.setY(getFieldHeight() / 6);
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
            defOneR.setY(getFieldHeight() / 6);
        }
        if(defOneR.collides(ceiling))
        {
            defOneR.setX(40);
            defOneR.setY(getFieldHeight() / 6);
        }
        if(defOneR.collides(floor))
        {
            defOneR.setX(40);
            defOneR.setY(getFieldHeight() / 6);
        }

        //Sets boundiares for ball 1
        if(ballOne.collides(wallL)){
            ballOne.setX(5);
        }
        if(ballOne.collides(wallR)){
            ballOne.setX(wallR.getX() - 10);
        }
        if(ballOne.collides(ceiling)){
            ballOne.setY(5);
        }
        if(ballOne.collides(floor)){
            ballOne.setY(floor.getY() - 10);
        }

        //Sets boundaries for bullet 1
        if(bulletOne.collides(wallL)){
            bulletHit();
            bullet1InMotion = false;
            bulletOne.setColor(Color.black);
            bulletOne.keepBulletWithBall(ballOne);
            bulletOne.stopShooting();


        }
        if(bulletOne.collides(wallR)){
            bulletHit();
            bullet1InMotion = false;
            bulletOne.setColor(Color.black);
            bulletOne.keepBulletWithBall(ballOne);
            bulletOne.stopShooting();

        }
        if(bulletOne.collides(ceiling)){
            bulletHit();
            bullet1InMotion = false;
            bulletOne.setColor(Color.black);
            bulletOne.keepBulletWithBall(ballOne);
            bulletOne.stopShooting();

        }
        if(bulletOne.collides(floor)){
            bulletHit();
            bullet1InMotion = false;
            bulletOne.setColor(Color.black);
            bulletOne.keepBulletWithBall(ballOne);
            bulletOne.stopShooting();

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
            bullet2InMotion = false;
            bulletTwo.setColor(Color.black);
            bulletTwo.keepBulletWithBall(ballTwo);
            bulletTwo.stopShooting();


        }
        if(bulletTwo.collides(wallR)){
            bulletHit();
            bullet2InMotion = false;
            bulletTwo.setColor(Color.black);
            bulletTwo.keepBulletWithBall(ballTwo);
            bulletTwo.stopShooting();

        }
        if(bulletTwo.collides(ceiling)){
            bulletHit();
            bullet2InMotion = false;
            bulletTwo.setColor(Color.black);
            bulletTwo.keepBulletWithBall(ballTwo);
            bulletTwo.stopShooting();

        }
        if(bulletTwo.collides(floor)){
            bulletHit();
            bullet2InMotion = false;
            bulletTwo.setColor(Color.black);
            bulletTwo.keepBulletWithBall(ballTwo);
            bulletTwo.stopShooting();

        }


        /***********************************************
         * Collisions between player controlled objects*
         ***********************************************/
        //Bullet 1 & Defender 2 L collision and health lowering
        if(bulletOne.collides(defTwoL) && bullet1InMotion)
        {
            bulletHit();
            bullet1InMotion = false;
            defTwoL.doDamage();
            bulletTwo.bulletCollision(ballOne);
        }
        //Bullet 1 & Defender 2 R collision and health lowering
        if(bulletOne.collides(defTwoR) && bullet1InMotion)
        {
            bulletHit();
            bullet1InMotion = false;
            defTwoR.doDamage();
            bulletOne.bulletCollision(ballOne);
        }

        //Bullet 2 & Defender 1 L collision and health lowering
        if(bulletTwo.collides(defOneL) && bullet2InMotion)
        {
            bulletHit();
            bullet2InMotion = false;
            defOneL.doDamage();
            bulletTwo.bulletCollision(ballTwo);
        }
        //Bullet 2 & Defender 1 R collision and health lowering
        if(bulletTwo.collides(defOneR) && bullet2InMotion)
        {
            bulletHit();
            bullet2InMotion = false;
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
