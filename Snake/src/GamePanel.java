
import java.awt.*;
//import java.awt.event.ActionListener;
import java.awt.event.*;
//import java.awt.event.KeyEvent;
import javax.swing.*;
//import javax.swing.JPanel;
import java.util.Random;

/*
 * [JPanel CLASS]
 * 
 * [ActionList  INTERFACE]
 * 
 * [Java.util.Timer CLASS]
 */
public class GamePanel extends JPanel implements ActionListener{
	//attributes
	/*
	 * static: belong to class, not object
	 * final: the specific value cannot be changed
	 */
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	//the size of every unit
	static final int UNIT_SIZE = 25; 
	//how many units the game panel have
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	//how fast the snake will move
	static final int DELAY = 200;
	//the (x,y) to specify a unit
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	//the size of the snake
	int bodyParts = 6;
	//how many apples the snake has eaten
	int applesEaten;
	//the (x,y) to specify an apple
	int appleX;
	int appleY;
	//the direction the snake move towards
	char direction = 'R';
	//whether the snake is moving 
	boolean running = false;
	
	Timer timer;
	//Random
	Random random;
	
	//constructor for the class
	GamePanel(){
		//make a random number
		random = new Random();
		//[Dimenson CLASS]
		/*
		 * Dimension class is a part of Java AWT. 
		 * It contains the height and width of a component in an integer as well as double precision. 
		 
		 	The use of Dimension class is 
		 	that many functions of Java AWT and Swing return dimension object.
		 	
		 	[Constructor of the Dimension class]
		 	Dimension(int w, int h) : It will create a new object with specified height and width.
		 	
		 */
		
		//setPreferredSize [Methods inherited from class javax.swing.JComponent]
		/*
		 * public void setPreferredSize(Dimension preferredSize)
			Sets the preferred size of this component. 
			If preferredSize is null, the UI will be asked for the preferred size.
			
			
			https://docs.oracle.com/javase/8/docs/api/javax/swing/JComponent.html#setPreferredSize-java.awt.Dimension-
		 */
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		
		//
		/*
		 * public void setBackground(Color bg)
		   Sets the background color of this component.
		 */
		/*
		 * java.awt.Color
		 * CLASS color
		 * Fields: static  black
		 * 			static BLACK
		 */
		this.setBackground(Color.black);
		
		/*
		 * public void setFocusable(boolean focusable)
Sets the focusable state of this Component to the specified value. This value overrides the Component's default focusability.
		 */
		this.setFocusable(true);
		/*
		 * Adds the specified key listener to receive key events from this component. If l is null, no exception is thrown and no action is performed.
		 */
		/*
		 * 
		 */
		this.addKeyListener(new MyKeyAdapter());
		startGame();
		
	}
	
	//startGame method
	public void startGame() {
		newApple();
		running = true;
		
		/*[key word - this]
		 * Definition and Usage
The this keyword refers to the current object in a method or constructor.

The most common use of the this keyword is to eliminate the confusion between class attributes and parameters with the same name (because a class attribute is shadowed by a method or constructor parameter). If you omit the keyword in the example above, the output would be "0" instead of "5".

this can also be used to:

Invoke current class constructor
Invoke current class method
Return the current class object
Pass an argument in the method call
Pass an argument in the constructor call
		 * 
		 */
		timer = new Timer(DELAY,this);
		/*
		 * 
void javax.swing.Timer.start()

Starts the Timer,causing it to start sending action eventsto its listeners.

		 */
		timer.start();
		
	}
	
	//paintComponent method
	public void paintComponent(Graphics g) {
		/*[Graphic class and Graphic object]
		 * A Graphics object encapsulates state information needed for the basic rendering operations that Java supports.
		 */
		
		/*[java super keyword]
		 * Using super to call the superclass of Dog (subclass)
		 */
		/*
		 * supe class: JPanel;
		 * Methods inherited from class javax.swing.JComponent
		 * 
		 * protected void paintComponent(Graphics g)
Calls the UI delegate's paint method, if the UI delegate is non-null. We pass the delegate a copy of the Graphics object to protect the rest of the paint code from irrevocable changes (for example, Graphics.translate).
		 */
		super.paintComponent(g);
		draw(g);
		
	}
	
	//draw method
	public void draw(Graphics g) {
		if(running) {
			//draw lines
			/*
			for(int i=0; i < SCREEN_HEIGHT/UNIT_SIZE;i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);
			}
			*/
			//draw apples
			/*public abstract void setColor(Color c)
Sets this graphics context's current color to the specified color. All subsequent graphics operations using this graphics context use this specified color.
			 * 
			 */
			g.setColor(Color.red);
			/*
			 * public abstract void fillOval(int x,
                              int y,
                              int width,
                              int height)
Fills an oval bounded by the specified rectangle with the current color.
			 */
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			
			//draw the snake
			for(int i = 0; i < bodyParts;i++) {
				if(i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				else {
					g.setColor(new Color(45,180,0));
					g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			
			//print the score
			g.setColor(Color.red);
			/*
			 * public abstract void setFont(Font font)
Sets this graphics context's font to the specified font. All subsequent text operations using this graphics context use this font. 

				Constructors
				Font(String name, int style, int size)
Creates a new Font from the specified name, style and point size.
			 */
			g.setFont(new Font("Ink Free",Font.BOLD, 40));
			/*
			 * Gets the FontMetrics for the specified Font
			 */
			FontMetrics metrics = getFontMetrics(g.getFont());
			/*
			 * public abstract void drawString(String str,
                                int x,
                                int y)
                  
                  Parameters:
str - the string to be drawn.
x - the x coordinate.
y - the y coordinate.
			 */
			g.drawString("Score: "+applesEaten,(SCREEN_WIDTH-metrics.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());
			
		}
		else {
			gameOver(g);
		}
		
	}
	
	//2024.2.27
	//new Apple method
	public void newApple() {
		/*
		 * int	nextInt(int bound)
Returns a pseudorandom, uniformly distributed int value between 0 (inclusive) and the specified value (exclusive), drawn from this random number generator's sequence.
		 */
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
		//at the end, multiplying a UNIT_SIZE, --->wants apple at the middle of each unit
		
		
		
	}
	
	//move method
	public void move() {
		for(int i = bodyParts; i > 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U':
			y[0] = y[0]-UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}
	
	//check whether eat an apple
	//x[0]y[0] is the snake head
	public void checkApple() {
		if((x[0] == appleX)&&(y[0] == appleY)) { //if the head meet the apple
			bodyParts++; 
			applesEaten++;
			newApple();//produce a new apple
		}
	}
	
	
	//checkCollisions method
	public void checkCollisions() {
		// checks if head collides with body
		for(int i = bodyParts; i  > 0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])){ //head == body
				running = false;
			}
		}
		
		//check if head touches left border
		if(x[0] < 0) {
			running = false;
		}
		//check if head touches the right border
		if(x[0] > SCREEN_WIDTH) {
			running = false;
		}
		//check if head touches top border
		if(y[0] < 0) {
			running = false;
		}
		//check if head touches bottom border
		if(y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		
		//check whether running is false
		if(running == false) {
			timer.stop();
		}
		
		
	}
	
	//gameOver method
	public void gameOver(Graphics g) {
		//score
		//print the score
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+applesEaten,(SCREEN_WIDTH-metrics1.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());
		
		
		//set the gameOVer text
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over",(SCREEN_WIDTH-metrics.stringWidth("Game Over"))/2,(SCREEN_HEIGHT/2));
	}
	
	
	//actionPerformed method
	//Invoked when an action occurs.
	//Specified by: actionPerformed(...) in ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
		
	}
	
	//inner class MyKeyAdapter
	/*
	 * 1) java.awt.event.KeyAdapter
	 * 2)public abstract class KeyAdapter
		extends Object
		implements KeyListener
		3) METHOD DETAILS--- public void keyPressed(KeyEvent e)
		Invoked when a key has been pressed.
	 */
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}  
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}

}
