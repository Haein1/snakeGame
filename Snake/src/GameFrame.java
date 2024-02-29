import javax.swing.JFrame; //import JFrame class
/*
 * [Java JFrame]
 * Thе Java JFramе is an еssеntial componеnt of Java Swing, 
 * which is a part of thе Java SWT(Standard Widgеt Toolkit). 
 * JFrame in Java is a class that allows you to crеatе and manage a top-lеvеl window in a Java application. 
 */
public class GameFrame extends JFrame{ 
	GameFrame(){  //create a constructor for GameFrame class
		//Adds a Swing component to the JFrame.
		this.add(new GamePanel()); 
		//Sets the title of the JFrame.
		this.setTitle("Snake");
		//Sets the default close operation for the JFrame. Common options include JFrame.EXIT_ON_CLOSE, JFrame.HIDE_ON_CLOSE, and JFrame.DO_NOTHING_ON_CLOSE.
		//A field that specifies the default operation to perform when the user closes the JFrame. It’s typically used with the setDefaultCloseOperation() method.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Controls whether the user can resize the JFrame.
		this.setResizable(false);
		/*
		 * Causes this Window to be sized to fit the preferred sizeand layouts of its subcomponents. The resulting width andheight of the window are automatically enlarged if eitherof dimensions is less than the minimum size as specifiedby the previous call to the setMinimumSize method. 

If the window and/or its owner are not displayable yet,both of them are made displayable before calculatingthe preferred size. The Window is validated after itssize is being calculated.

		 * 
		 */
		this.pack();
		//Sets the visibility of the JFrame. Pass true to make it visible and false to hide it.
		this.setVisible(true);
		//Sets the location of the window relative to the specifiedcomponent according to the following scenarios. 
		//•If the component is null, or the GraphicsConfiguration associated with this component is null, the window is placed in the center of thescreen.
		this.setLocationRelativeTo(null);
		
		
	}
}
