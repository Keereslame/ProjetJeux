import hevs.graphics.FunGraphics;
import hevs.graphics.utils.GraphicsBitmap;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Snake {
	enum Direction {
		LEFT, RIGHT, UP, DOWN
	};

	// Attributs de classe
	int length;
	Direction dirSnake;
	int position[][] = new int[32][32];
	int posx = position.length / 2;
	int posy = position[0].length / 2;
	GraphicsBitmap head = new GraphicsBitmap("/Pictures/Colored/red.png");
	GraphicsBitmap head2 = new GraphicsBitmap("/Pictures/Colored/red.png");
	GraphicsBitmap head3 = new GraphicsBitmap("/Pictures/Colored/red.png");
	GraphicsBitmap head4 = new GraphicsBitmap("/Pictures/Colored/red.png");
	
	GraphicsBitmap body = new GraphicsBitmap("/Pictures/Colored/blue.png");
	GraphicsBitmap apple = new GraphicsBitmap("/Pictures/cherry.png");
	GraphicsBitmap rock = new GraphicsBitmap("/Pictures/scifiEnvironment_19.png");
	GraphicsBitmap queue = new GraphicsBitmap("/Pictures/Colored/yellow.png");
	GraphicsBitmap coude = new GraphicsBitmap("/Pictures/Colored/green.png");
	GraphicsBitmap vide = new GraphicsBitmap("/Pictures/Colored/grey_clair.png");
	
	
	
	
	

	// images pixels
	GraphicsBitmap v = new GraphicsBitmap("/Pictures/pixel/v.png");
	GraphicsBitmap p = new GraphicsBitmap("/Pictures/pixel/p.png");
	GraphicsBitmap mgd = new GraphicsBitmap("/Pictures/pixel/mgd.png");
	GraphicsBitmap mhb = new GraphicsBitmap("/Pictures/pixel/mhb.png");
	GraphicsBitmap tnd = new GraphicsBitmap("/Pictures/pixel/tnd.png");
	GraphicsBitmap tng = new GraphicsBitmap("/Pictures/pixel/tng.png");
	GraphicsBitmap tnb = new GraphicsBitmap("/Pictures/pixel/tnb.png");
	GraphicsBitmap tnh = new GraphicsBitmap("/Pictures/pixel/tnh.png");
	GraphicsBitmap tcd = new GraphicsBitmap("/Pictures/pixel/tcd.png");
	GraphicsBitmap tcg = new GraphicsBitmap("/Pictures/pixel/tcg.png");
	GraphicsBitmap tch = new GraphicsBitmap("/Pictures/pixel/tch.png");
	GraphicsBitmap tcb = new GraphicsBitmap("/Pictures/pixel/tcb.png");
	GraphicsBitmap cdtd = new GraphicsBitmap("/Pictures/pixel/cdtd.png");
	GraphicsBitmap cdtg = new GraphicsBitmap("/Pictures/pixel/cdtg.png");
	GraphicsBitmap cdtb = new GraphicsBitmap("/Pictures/pixel/cdtb.png");
	GraphicsBitmap cdth = new GraphicsBitmap("/Pictures/pixel/cdth.png");
	GraphicsBitmap cgb = new GraphicsBitmap("/Pictures/pixel/cgb.png");
	GraphicsBitmap cbd = new GraphicsBitmap("/Pictures/pixel/cbd.png");
	GraphicsBitmap cgh = new GraphicsBitmap("/Pictures/pixel/cgh.png");
	GraphicsBitmap chd = new GraphicsBitmap("/Pictures/pixel/chd.png");
	GraphicsBitmap qcd = new GraphicsBitmap("/Pictures/pixel/qcd.png");
	GraphicsBitmap qcg = new GraphicsBitmap("/Pictures/pixel/qcg.png");
	GraphicsBitmap qch = new GraphicsBitmap("/Pictures/pixel/qch.png");
	GraphicsBitmap qcb = new GraphicsBitmap("/Pictures/pixel/qcb.png");

	int nbApple = 0;
	int score = 0;

	static int GRAPHICS_WIDTH = 320;
	static int GRAPHICS_HEIGHT = 320;

	// Creation de la fenetre de jeu
	public static FunGraphics display = new FunGraphics(GRAPHICS_WIDTH, GRAPHICS_HEIGHT, 1200, 400, "Snake", true);

	// Constantes de classe
	public static int nbWallLevel1 = 0;
	public static int nbWallLevel2 = 5;
	public static int nbWallLevel3 = 20;

	public static int tailleWallLevel1 = 0;
	public static int tailleWallLevel2 = 3;
	public static int tailleWallLevel3 = 4;

	public static int fpsLevel1 = 10;
	public static int fpsLevel2 = 30;
	public static int fpsLevel3 = 50;

	// Savoir si on est train de jouer ou non
	boolean play = false;

	Scanner scan = new Scanner(System.in);

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

	public void setWidth(int width) {
		GRAPHICS_WIDTH = width;
	}

	public void setHeight(int height) {
		GRAPHICS_HEIGHT = height;
	}

	// methode d'affichage du menu
	public void updateGraphicsViewMenu() {
		display.clear();
		display.drawString(90, GRAPHICS_HEIGHT / 4, "SNAKE by Philippine & Mathieu", Color.BLACK, 10);
		display.drawString(GRAPHICS_WIDTH / 3, GRAPHICS_HEIGHT / 2, "1) Level 1", Color.BLACK, 10);
		display.drawString(GRAPHICS_WIDTH / 3, GRAPHICS_HEIGHT / 2 + 50, "2) Level 2", Color.BLACK, 10);
		display.drawString(GRAPHICS_WIDTH / 3, GRAPHICS_HEIGHT / 2 + 100, "3) Level 3", Color.BLACK, 10);
		display.drawString(GRAPHICS_WIDTH / 3, GRAPHICS_HEIGHT / 2 + 200, "Please select one Leevel : ", Color.BLACK,
				10);
		int level = Dialogs.getInt("Your choice:");

		switch (level) {
		case 1:
			nbWallLevel1 = 0;
			tailleWallLevel1 = 0;
			fpsLevel1 = 10;
			break;
		case 2:
			nbWallLevel2 = 5;
			tailleWallLevel2 = 3;
			fpsLevel2 = 30;
			break;
		case 3:
			nbWallLevel3 = 20;
			tailleWallLevel3 = 4;
			fpsLevel3 = 50;
			break;
		}
	}

	// methode d'affichage
	public void updateGraphicsViewGame() {
		
		double r = 0.2;
		synchronized (display.frontBuffer) {
			display.clear();
			display.drawString(20, 20, "Timer: ");

			for (int i = 0; i < position.length; i++) {
				for (int j = 0; j < position.length; j++) {
//
//					switch (position[i][j]) {
//
//					case 0:
//						display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, v);
//						break;
//					case -1:
//						display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, p);
//						break;
//					case -2:
//						if (position[i][j - 1] == -2 || position[i][j + 1] == -2) {
//							display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, mgd);
//						} else {
//							display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, mhb);
//						}
//						break;
//					case 1:
//						if (position[i][j - 1] > 0 && position[i][j + 1] == -1) {
//							display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, tnd);
//						} else if (position[i][j + 1] > 0 && position[i][j - 1] == -1) {
//							display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, tng);
//						} else if (position[i - 1][j] > 0 && position[i + 1][j] == -1) {
//							display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, tnb);
//						} else if (position[i + 1][j] > 0 && position[i - 1][j] == -1) {
//							display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, tnh);
//						} else if (position[i][j - 1] > 0 && position[i][j + 1] == 0) {
//							display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, tcd);
//						} else if (position[i][j + 1] > 0 && position[i][j - 1] == 0) {
//							display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, tcg);
//						} else if (position[i - 1][j] > 0 && position[i + 1][j] == 0) {
//							display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, tch);
//						} else if (position[i + 1][j] > 0 && position[i - 1][j] == 0) {
//							display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, tcb);
//						}
//
//						break;
//
//					default:
//						if (position[i][j] == length) {
//							if (position[i][j - 1] == length - 1) {
//								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, qcd);
//							} else if (position[i][j + 1] == length - 1) {
//								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, qcg);
//							} else if (position[i - 1][j] == length - 1) {
//								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, qch);
//							} else if (position[i + 1][j] == length - 1) {
//								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, qcb);
//							}
//						} else {
//							if (position[i][j - 1] > position[i][j + 1]) {
//								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, cdtd);
//							} else if (position[i][j - 1] < position[i][j + 1]) {
//								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, cdtg);
//							} else if (position[i - 1][j] > position[i + 1][j]) {
//								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, cdtb);
//							} else if (position[i - 1][j] < position[i + 1][j]) {
//								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, cdth);
//							} 
//							
//							
//							else if (position[i][j - 1] > 0 && position[i + 1][j] > 0) {
//								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, cgb);
//							} else if (position[i][j + 1] > 0 && position[i + 1][j] > 0) {
//								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, cbd);
//							} else if (position[i][j - 1] > 0 && position[i - 1][j] > 0) {
//								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, cgh);
//							} else if (position[i][j + 1] > 0 && position[i - 1][j] > 0) {
//								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, r, chd);
//							}
//						}
//						break;
//					}

					switch (position[i][j]) {
					case 0:
						display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, vide);
						break;

					case 1:
						//tête corp à droite
//						if (i < position.length && j>0 && position[i][j - 1] > 0 && position[i][j + 1] == 0) {
							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, head);
//						//tête corp à gauche
//						} else if (j>0 && j<position.length && position[i][j + 1] > 0 && position[i][j - 1] == 0) {
//							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, head);
//						//tête corp en haut
//						} else if (i>0 && i<position.length && position[i - 1][j] > 0 && position[i + 1][j] == 0) {
//							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, head);
//						//tête corp en bas
//						} else if (i>0 && i<position.length && position[i + 1][j] > 0 && position[i - 1][j] == 0) {
//							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, head);
//						}
//						
						

						break;
					case -1:
						display.drawTransformedPicture(i * 10 + 5, j * 10, 0.0, 0.25, apple);
						break;
					case -2:
//						if (position[i][j - 1] == -2 || position[i][j + 1] == -2) {
							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, rock);
//						} else {
//							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, rock);
//						}
						
						
						break;

					default:
						if (position[i][j] == length) {

							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, queue);

						} else {
//						//coude haut gauche
//						if(i > 0 && j>0 && position[i][j - 1] > 0 && position[i - 1][j] > 0) {
//							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, coude);
//							
//						//coude haut droite
//						}else if(i <position.length && j>0 && position[i][j - 1] > 0 && position[i + 1][j] > 0) {
//							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, coude);
//							
//						//coude bas gauche
//						}else if(i > 0 && j<position.length && position[i][j +1] > 0 && position[i - 1][j] > 0) {
//							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, coude);
//						//coude bas droite
//						}else if(i < position.length && j<position.length && position[i][j +1] > 0 && position[i + 1][j] > 0) {
//							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, coude);
//						//corp droit tête à droite
//						}else if(j>0&&j<position.length&&position[i][j - 1] > position[i][j + 1]){		
							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, body);
//						//corp droit tête à gauche
//						}else if(j>0&&j<position.length&&position[i][j - 1] < position[i][j + 1]) {
//							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, body);
//						//corp droit tête bas
//						}else if(i>0&&i<position.length&&position[i - 1][j] > position[i + 1][j]) {
//							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, body);
//						//corp droit tête haut
//						}else if(i>0&&i<position.length&&position[i - 1][j] < position[i + 1][j]) {
//							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, body);
//						
//						}

						}
						break;
					}
				}
			}
		}

	display.syncGameLogic(fpsLevel1);

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
			int x = (int) (Math.random() * (position.length - wallLength));
			int y = (int) (Math.random() * (position[0].length - wallLength));
			for (int i = 0; i < wallLength; i++) {
				if (vertical) {
					if (position[x][y + i] == 0) {
						position[x][y + i] = -2;
					}
				} else {
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
		display.clear(Color.WHITE);
		display.drawString(GRAPHICS_WIDTH / 3, GRAPHICS_HEIGHT / 3, "Sorry, you lose!", Color.RED, 10);
		display.drawString(100, 200, "Your score is : " + score, Color.BLUE, 10);

	}

	public void play() {
		updateGraphicsViewMenu();
		wall(nbWallLevel2, tailleWallLevel2);
		direction();
		while (play) {

			move(dirSnake);
			if (nbApple == 0) {
				apple();
			}
			updateGraphicsViewGame();
		}
		gameover();
	}

}
