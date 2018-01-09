import hevs.graphics.FunGraphics;

public class Display {
	final static int GRAPHICS_WIDTH = 64;
	final static int GRAPHICS_HEIGHT = 64;
	final static int STARTING_POSITION_X = 32;
	final static int STARTING_POSITION_Y = 32;

	// Display surface to draw on
	public static FunGraphics display;
	
	// Create and set the windows up
	public Display(){
		display = new FunGraphics(GRAPHICS_WIDTH, GRAPHICS_HEIGHT);

	}
}
	
