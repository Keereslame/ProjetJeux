import hevs.graphics.FunGraphics;
import hevs.graphics.utils.GraphicsBitmap;

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
	int posx = position.length / 2;
	int posy = position[0].length / 2;
	GraphicsBitmap head = new GraphicsBitmap("/Pictures/Colored/red.png");
	GraphicsBitmap body = new GraphicsBitmap("/Pictures/Colored/grey_clair.png");
	GraphicsBitmap apple = new GraphicsBitmap("/Pictures/cherry.png");
	GraphicsBitmap rock = new GraphicsBitmap("/Pictures/scifiEnvironment_19.png");
	int nbApple = 0;
	int score = 0 ;

	// Constantes pour la fenetre
	final static int GRAPHICS_WIDTH = 640;
	final static int GRAPHICS_HEIGHT = 640;

	// Savoir si on est train de jouer ou non
	boolean play = false;

	// Création de la fenêtre de jeu
	public static FunGraphics display = new FunGraphics(GRAPHICS_WIDTH, GRAPHICS_HEIGHT);
	
	// Constructeur
	public Snake(int length, Direction direction) {

		this.length = length;
		this.dirSnake = direction;
		this.position[posx][posy] = 1;
		for (int i = 2; i <= length; i++) {

			if (dirSnake == Direction.LEFT) {
				position[posx + (i - 1)][posy] = i;

			} else if (dirSnake == Direction.DOWN) {
				position[posx][posy - (i - 1)] = i;

			} else if (dirSnake == Direction.RIGHT) {
				position[posx - (i - 1)][posy] = i;

			} else {
				position[posx][posy + (i - 1)] = i;

			}
		}
		play = true;
		apple();
		
		

	}
	
	//methode d'affichage
	public void screen(){
		
			synchronized(display.frontBuffer){
				display.clear();
				display.drawString(20, 20, "Timer: ");

				for (int i = 0; i < GRAPHICS_WIDTH; i += 10) {
					for (int j = 0; j < GRAPHICS_HEIGHT; j += 10) {

						if (position[i / 10][j / 10] == 1) {
							display.drawTransformedPicture(i, j, 0.0, 0.25, head);
						} else if (position[i / 10][j / 10] > 1) {
							display.drawTransformedPicture(i, j, 0.0, 0.25, body);
						} else if (position[i / 10][j / 10] == -1) {
							display.drawTransformedPicture(i, j, 0.0, 0.25, apple);
						} else if (position[i / 10][j / 10] == -2) {
							display.drawTransformedPicture(i, j, 0.0, 0.25, rock);
						}
					}
				}
			}

			display.syncGameLogic(10);

		}	

	// Methode qui lit les fleches
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
	}

	// Methode pour le mouvement du snake
	public void move(Direction d) {
		int headX = 0;
		int headY = 0;
		int queuX = 0;
		int queuY = 0;

		for (int i = 0; i < position.length; i++) {
			for (int j = 0; j < position[i].length; j++) {
				if (position[i][j] == 1) {
					headX = i;
					headY = j;
				}
				if (position[i][j] == length) {
					queuX = i;
					queuY = j;
					position[i][j] = 0;
				}
				if (position[i][j] > 0) {
					position[i][j]++;
				}
			}
		}
		length--;

		switch (d) {
		case LEFT:
			headX--;
			break;
		case RIGHT:
			headX++;
			break;
		case UP:
			headY--;
			break;
		case DOWN:
			headY++;
			break;
		}

		if (headX == position.length) {
			headX = 0;
		} else if (headY == position.length) {
			headY = 0;
		} else if (headX == 0) {
			headX = position.length - 1;
		} else if (headY == 0) {
			headY = position.length - 1;
		}

		if (position[headX][headY] == -1) {
			length += 2;
			position[headX][headY] = 1;
			position[queuX][queuY] = length;
			nbApple--;
			score++;
		} else if (position[headX][headY] == 0) {
			position[headX][headY] = 1;
			length++;
		} else {
			play = false;
		}

	}

	// Creation d'obstacle
	public void wall(int nbObstacles, int wallLength) {
		int nbWall = 0;

		while (nbWall < nbObstacles) {
			boolean wallFlat = false;
			boolean wallStraight = false;
			if (Math.random() < 0.5) {
				wallFlat = true;
			} else {
				wallStraight = true;
			}
			int x = (int) (Math.random() * position.length);
			int y = (int) (Math.random() * position.length);
			for (int i = 0; i < wallLength; i++) {
				if (wallStraight && x < position.length-wallLength) {
					if (position[x + i][y] == 0) {
						position[x + i][y] = -2;
					}

				}
				if (wallFlat && y < position.length-wallLength) {
					if (position[x][y + i] == 0) {
						position[x][y + i] = -2;
					}
				}
			}
			nbWall++;
		}
	}

	// Creation de pomme
	public void apple() {
		int x = (int) (Math.random() * position.length);
		int y = (int) (Math.random() * position.length);
		if (position[x][y] == 0) {
			position[x][y] = -1;
			
		}
		nbApple ++;
	}

	// Methode s'il y a un echec
	public void gameover() {
		play = false;
		display.clear(Color.WHITE);
		display.drawString(100, 200, "Sorry, you lose!", Color.RED, 50);
		display.drawString(200, 500, "Your score is : "+ score, Color.BLUE, 50);
		

	}
	
	public void play(){
		
		while(play){
			direction();
			move(dirSnake);
			if(nbApple == 0){
				apple();
			}
			screen();		
		}
		gameover();
	}

}
