import hevs.graphics.FunGraphics;

public class Level1 {
	// Constantes pour la fenetre
		final static int GRAPHICS_WIDTH = 300;
		final static int GRAPHICS_HEIGHT = 300;
		
	// Création de la fenêtre de jeu
	public static FunGraphics display = new FunGraphics(GRAPHICS_WIDTH, GRAPHICS_HEIGHT);
	
	//Constantes de classe
	public static int nbWall = 0;
	public static int tailleWall = 3;
	
	public static int fps = 10;
}
