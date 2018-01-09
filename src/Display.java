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
	
	// Create and set the windows up
	public Display(){
		display = new FunGraphics(GRAPHICS_WIDTH, GRAPHICS_HEIGHT);
	}
	
	//Dessiner une pomme
	public void apple(){
		 
	}
	
	//Dessiner un mur
	public void wall(){
		
	}
	
	//Dessiner une tête
	public void head(){
		
	}
	
	//Dessiner un corps
	public void body(){
		
	}
	
	//Afficher les éléments
	public void draw(){
		for(int i = 0; i < snake.position.length; i++){
			for(int j = 0; j < snake.position[i].length; j++){
				switch (snake.position[i][j]){
				case -1:
					
					

				}
			}
		}
	}
}
	
