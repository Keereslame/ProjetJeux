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
	int position[][] = new int[Level.GRAPHICS_WIDTH / 10][Level.GRAPHICS_HEIGHT / 10];
	int posx = position.length / 2;
	int posy = position[0].length / 2;
	GraphicsBitmap head = new GraphicsBitmap("/Pictures/Colored/red.png");
	GraphicsBitmap body = new GraphicsBitmap("/Pictures/Colored/grey_clair.png");
	GraphicsBitmap apple = new GraphicsBitmap("/Pictures/cherry.png");
	GraphicsBitmap rock = new GraphicsBitmap("/Pictures/scifiEnvironment_19.png");
	int nbApple = 0;
	int score = 0;

	// Savoir si on est train de jouer ou non
	boolean play = false;

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

	// methode d'affichage
	public void updateGraphicsView() {

		synchronized (Level.display.frontBuffer) {
			Level.display.clear();
			Level.display.drawString(20, 20, "Timer: ");

			for (int i = 0; i < Level.GRAPHICS_WIDTH; i += 10) {
				for (int j = 0; j < Level.GRAPHICS_HEIGHT; j += 10) {
					
					switch(position[i/10][j/10]){
					case 1:
						Level.display.drawTransformedPicture(i + 5, j + 5, 0.0, 0.25, head);
						break;
					case -1:
						Level.display.drawTransformedPicture(i + 5, j, 0.0, 0.25, apple);
						break;
					case -2:
						Level.display.drawTransformedPicture(i + 5, j + 5, 0.0, 0.25, rock);
						break;
					case 0:
						break;
					default:
						Level.display.drawTransformedPicture(i + 5, j + 5, 0.0, 0.25, body);
						break;
					}
				}
			}
		}

		Level.display.syncGameLogic(Level.fps);

	}

	// Methode qui lit les fleches
	public void direction() {
		Level.display.setKeyManager(new KeyAdapter() {

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
		} else if (headX == -1) {
			headX = position.length - 1;
		} else if (headY == -1) {
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
		int taille = 0;
		
		while (nbWall < nbObstacles) {
			boolean vertical = false;
		
			if (Math.random() < 0.5) {
				vertical = false;
			} else {
				vertical = true;
			}
			int x = (int) (Math.random() * (position.length-wallLength));
			int y = (int) (Math.random() * (position[0].length-wallLength));
			for (int i = 0; i < wallLength; i++) {
				if (vertical) {
					if (position[x][y + i] == 0) {
						position[x][y + i] = -2;
					}
				}
				else
				{
					if (position[x + i][y] == 0) {
						position[x + i][y] = -2;
					}
					
				}
			}
			nbWall++;
		}
	}

	// Creation de pomme
	public void apple() {
		int x = (int) (Math.random() * (position.length));
		int y = (int) (Math.random() * (position[0].length));

		if (position[x][y] == 0) {
			position[x][y] = -1;
			nbApple++;
		}

	}

	// Methode s'il y a un echec
	public void gameover() {
		play = false;
		Level.display.clear(Color.WHITE);
		Level.display.drawString(Level.GRAPHICS_WIDTH / 3, Level.GRAPHICS_HEIGHT / 3, "Sorry, you lose!", Color.RED,
				10);
		Level.display.drawString(100, 200, "Your score is : " + score, Color.BLUE, 10);

	}

	public void play() {
		wall(Level.nbWall, Level.tailleWall);
		direction();
		while (play) {
			
			move(dirSnake);
			if (nbApple == 0) {
				apple();
			}
			updateGraphicsView();
		}
		gameover();
	}

}
