import hevs.graphics.FunGraphics;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Snake {
	enum Direction {
		LEFT, RIGHT, UP, DOWN
	};

	// Attributs de classe
	int length;
	Direction dirSnake;
	int position[][] = new int[64][64];
	int posx = position.length/2;
	int posy = position[0].length/2;

	//Constantes pour la fenetre
	final static int GRAPHICS_WIDTH = 640;
	final static int GRAPHICS_HEIGHT = 640;
	
	//Savoir si on est train de jouer ou non
	boolean play = false;

	// Display surface to draw on
	public static FunGraphics display = new FunGraphics(GRAPHICS_WIDTH, GRAPHICS_HEIGHT);

	// Constructeur
	public Snake(int length, Direction direction) {

		this.length = length;
		this.dirSnake = direction;
		this.position[posx][posy] = 1;
		for (int i = 2; i <= length; i++) {

			if (dirSnake == Direction.LEFT) {
				position[posx+(i-1)][posy] = i;

			} else if (dirSnake == Direction.DOWN) {
				position[posx][posy-(i-1)] = i;

			} else if (dirSnake == Direction.RIGHT) {
				position[posx-(i-1)][posy ] = i;

			} else {
				position[posx][posy + (i-1)] = i;

			}
		}
		play = true;

		
		

	}

	//Methode qui lit les fleches
	public void direction() {
		display.setKeyManager(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					dirSnake = Direction.RIGHT;
					
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					dirSnake = Direction.LEFT;
					
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					dirSnake = Direction.UP;
				
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					dirSnake = Direction.DOWN;
				}
			}

		});

		while (true) {
			
			display.clear();

			move(dirSnake);
			for(int i=0;i< GRAPHICS_WIDTH;i+=10){
				for(int j=0;j < GRAPHICS_HEIGHT; j+=10){
					if(position[i/10][j/10] == 1){
						display.setColor(Color.RED);
						display.drawCircle(i, j, 5);
					}else if(position[i/10][j/10] > 1){
						display.setColor(Color.BLACK);
						display.drawCircle(i, j, 5);
					}else if(position[i/10][j/10] == -1){
						display.setColor(Color.BLUE);
						display.drawRect(i, j, 15, 15);
					}else if(position[i/10][j/10] == -2){
						display.setColor(Color.ORANGE);
						display.drawRect(i, j, 30, 30);
					}
				}
			}
			
			display.syncGameLogic(10);

		}
	}

	//Methode pour bouger le snake selon la direction
	public void move(Direction d) {
		int headX = 0;
		int headY = 0;
		int queuX = 0;
		int queuY = 0;
		
		for(int i = 0; i < position.length; i++){
			for(int j = 0; j < position[i].length; j++){
				if(position[i][j] == 1){
					headX = i;
					headY = j;
				}
				if(position[i][j] == length){
					queuX = i;
					queuY = j;
					position[i][j] = 0;
				}
				if(position[i][j] > 0){
					position[i][j]++;
				}
			}
		}
		length--;

		switch (d) {
		case LEFT:
			headX --;
			break;
		case RIGHT:
			headX ++;
			break;
		case UP:
			headY --;
			break;
		case DOWN:
			headY ++;
			break;
		}
		
		if(headX == position.length){
			headX = 1;
		
		}else if(headY == position.length){
			headY = 1;
		}

		if(position[headX][headY] == -1){
			length++;
			position[headX][headY] = 1;
			position[queuX][queuY] = length;
		}else if(position[headX][headY] == 0){
			position[headX][headY] = 1;	
			length++;
		}else{
			gameover();
		}
	}
	


	//Creation d'obstacle
	/*public void wall(int nbObstacles){
		int nbWall = 0;

		while(nbWall < nbObstacles){
			int x = (int) (Math.random()*GRAPHICS_WIDTH/10);
			int y = (int) (Math.random()*GRAPHICS_HEIGHT/10);
			if(position[x][y] == 0){
				position[x][y] = -2;
			}
			nbWall ++;
		}
	}*/
	
	//Creation de pomme
	/*public void apple(){
		int x = (int) (Math.random()*GRAPHICS_WIDTH/10);
		int y = (int) (Math.random()*GRAPHICS_HEIGHT/10);
		if(position[x][y] == 0){
			position[x][y] = -1;
		}
	}*/

	//Methode si'il y a un echec
	public void gameover(){
		display.clear();
		System.exit(1);
		play = false;
		
	}

}
