import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;

public abstract class Game extends JFrame {
 private boolean _isSetup = false;
 private boolean _initialized = false;
 private ArrayList _ObjectList = new ArrayList();
 private Timer _t;

 private boolean shootLeft = false;
    private boolean shootRight = false;
    private boolean shootUp = false;
    private boolean shootDown = false;
 private boolean ballUp = false;
 private boolean ballDown = false;
 private boolean ballLeft = false;
 private boolean ballRight = false;
 private boolean defUp = false;
 private boolean defDown = false;
 private boolean defLeft = false;
 private boolean defRight = false;
 private boolean changeColor = false;

 public boolean UpKeyPressed(){
   return changeColor;
 }
  public boolean IKeyPressed() {
  return defUp;
 }
  public boolean OneKeyPressed(){
    return shootLeft;
  }
    public boolean TwoKeyPressed(){
        return shootRight;
    }
    public boolean ThreeKeyPressed(){
        return shootUp;
    }
    public boolean FourKeyPressed(){
        return shootDown;
    }
  public void bulletHit(){
    shootLeft=false;
      shootRight=false;
      shootUp=false;
      shootDown=false;
  }
 public boolean KKeyPressed() {
  return defDown;
 }

 public boolean JKeyPressed() {
  return defLeft;
 }

 public boolean LKeyPressed() {
  return defRight;
 }
 public boolean WKeyPressed() {
  return ballUp;
 }

 public boolean SKeyPressed() {
  return ballDown;
 }

 public boolean AKeyPressed() {
  return ballLeft;
 }

 public boolean DKeyPressed() {
  return ballRight;
 }

 /**
  * When implemented, this will allow the programmer to initialize the game
  * before it begins running
  *
  * Adding objects to the game and setting their initial positions should be
  * done here.
  *
  * @see GameObject
  */
 public abstract void setup();

 /**
  * When the game begins, this method will automatically be executed every
  * millisecond
  *
  * This may be used as a control method for checking user input and
  * collision between any game objects
  */
 public abstract void act();

 /**
  * Sets up the game and any objects.
  *
  * This method should never be called by anything other than a <code>main</code>
  * method after the frame becomes visible.
  */
 public void initComponents() {
  getContentPane().setBackground(Color.black);
  setup();
  for (int i = 0; i < _ObjectList.size(); i++) {
    GameObject o = (GameObject)_ObjectList.get(i);
    o.repaint();
  }
  _t.start();
 }

 /**
  * Adds a game object to the screen
  *
  * Any added objects will have their <code>act</code> method called every
  * millisecond
  *
  * @param o  the <code>GameObject</code> to add.
  * @see GameObject#act()
  */
 public void add(GameObject o) {
  _ObjectList.add(o);
  getContentPane().add(o);
 }

 /**
  * Removes a game object from the screen
  *
  * @param o  the <code>GameObject</code> to remove
  * @see GameObject
  */
 public void remove(GameObject o) {
  _ObjectList.remove(o);
  getContentPane().remove(o);
 }

 /**
  * Sets the millisecond delay between calls to <code>act</code> methods.
  *
  * Increasing the delay will make the game run "slower." The default delay
  * is 1 millisecond.
  *
  * @param delay the number of milliseconds between calls to <code>act</code>
  * @see Game#act()
  * @see GameObject#act()
  */
 public void setDelay(int delay) {
  _t.setDelay(delay);
 }

 public void setBackground(Color c) {
  getContentPane().setBackground(c);
 }


 public Game() {

  setSize(600, 600);
  getContentPane().setBackground(Color.black);
  getContentPane().setLayout(null);
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem menuFileExit = new JMenuItem("Leave this B");
        menuBar.add(menuFile);
        menuFile.add(menuFileExit);
        setJMenuBar(menuBar);
        setTitle("Pong");

        // Add window listener.
        addWindowListener (
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
       menuFileExit.addActionListener(
         new ActionListener() {
          public void actionPerformed(ActionEvent e) {
           System.exit(0);
          }
         }
        );
       _t = new Timer(1, new ActionListener() {
         public void actionPerformed(ActionEvent e) {
       act();
       for (int i = 0; i < _ObjectList.size(); i++) {
        GameObject o = (GameObject)_ObjectList.get(i);
        o.act();
       }
         }
       });
       addKeyListener(new KeyListener() {
   public void keyTyped(KeyEvent e) {
   }

   public void keyPressed(KeyEvent e) {
    char pressed = Character.toUpperCase(e.getKeyChar());
    switch (pressed) {
     case 'W' : ballUp = true; break;
     case 'S' : ballDown = true; break;
     case 'A' : ballLeft = true; break;
     case 'D' : ballRight = true; break;
     case 'I' : defUp = true; break;
     case 'K' : defDown = true; break;
     case 'J' : defLeft = true; break;
     case 'L' : defRight = true; break;
     case '1' : shootLeft = true; break;
        case '2' : shootRight = true; break;
        case '3' : shootUp = true; break;
        case '4' : shootDown = true; break;
      case KeyEvent.VK_UP : changeColor = true; break;

  }

   }

   public void keyReleased(KeyEvent e) {
    char released = Character.toUpperCase(e.getKeyChar());
    switch (released) {
     case 'W' : ballUp = false; break;
     case 'S' : ballDown = false; break;
     case 'A' : ballLeft = false; break;
     case 'D' : ballRight = false; break;
     case 'I' : defUp = false; break;
     case 'K' : defDown = false; break;
     case 'J' : defLeft = false; break;
     case 'L' : defRight = false; break;
          case KeyEvent.VK_UP : changeColor = false; break;

    }
   }

       });
   }

 /**
  * Starts updates to the game
  *
  * The game should automatically start.
  *
  * @see Game#stopGame()
  */
 public void startGame() {
  _t.start();
 }

 /**
  * Stops updates to the game
  *
  * This can act like a "pause" method
  *
  * @see Game#startGame()
  */
 public void stopGame() {
  _t.stop();
 }

 /**
  * Displays a dialog that says "Player 1 Wins!"
  *
  */
 public void p1Wins() {
  _WinDialog d = new _WinDialog(this, "Darn, You got caught!");
  d.setVisible(true);
 }


 /**
  * Displays a dialog that says "Player 2 Wins!"
  *
  */
 public void p2Wins() {
  _WinDialog d = new _WinDialog(this, "You died :(");
  d.setSize(300,100);
  d.setVisible(true);
 }

 /**
  * Gets the pixel width of the visible playing field
  *
  * @return a width in pixels
  */
 public int getFieldWidth() {
  return getContentPane().getBounds().width;
 }

 /**
  * Gets the pixel height of the visible playing field
  *
  * @return a height in pixels
  */
 public int getFieldHeight() {
  return getContentPane().getBounds().height;
 }

 class _WinDialog extends JDialog {
  JButton ok = new JButton("OK");
  _WinDialog(JFrame owner, String title) {
   super(owner, title);
   Rectangle r = owner.getBounds();
   setSize(200, 100);
   setLocation(r.x + r.width / 2 - 100, r.y + r.height / 2 - 50);
   getContentPane().add(ok);
   ok.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
     _WinDialog.this.setVisible(false);
    }
   });
  }
 }
}
