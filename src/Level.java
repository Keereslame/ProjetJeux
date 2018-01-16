import hevs.graphics.FunGraphics;

public class Level {
	// Constantes pour la fenetre
		final static int GRAPHICS_WIDTH = 640;
		final static int GRAPHICS_HEIGHT = 640;
		
	// Creation de la fenetre de jeu
	public static FunGraphics display = new FunGraphics(GRAPHICS_WIDTH, GRAPHICS_HEIGHT, 1200, 400, "Snake", true);
	
	//Constantes de classe
	public static int nbWall = 3;
	public static int tailleWall = 3;
	
	public static int fps = 10;
}
