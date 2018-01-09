import hevs.graphics.FunGraphics;

public class Display {
	//Constants
	final static int GRAPHICS_WIDTH = 640;
	final static int GRAPHICS_HEIGHT = 640;
	final static int STARTING_POSITION_X = 320;
	final static int STARTING_POSITION_Y = 320;

	// Display surface to draw on
	public static FunGraphics display;
	
	//
	Snake snake;
	Food food;
	
	// Create and set the windows up
	public Display(){
		display = new FunGraphics(GRAPHICS_WIDTH, GRAPHICS_HEIGHT);
	}
}
	
