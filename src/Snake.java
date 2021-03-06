import hevs.graphics.FunGraphics;
import hevs.graphics.utils.GraphicsBitmap;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Snake {
	enum Direction {
		LEFT, RIGHT, UP, DOWN
	};

	// Attributs de classe
	int length = 6;
	Direction dirSnake = Direction.RIGHT;
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
	GraphicsBitmap mur = new GraphicsBitmap("/Pictures/pixel/mur.png");

	int nbApple = 0;
	int score1 = 0;
	int score2 = 0;
	int score3 = 0;
	long begin;
	long end;

	private static int GRAPHICS_WIDTH = 832;
	private static int GRAPHICS_HEIGHT = 832;

	// Creation de la fenetre de jeu
	public static FunGraphics display = new FunGraphics(GRAPHICS_WIDTH, GRAPHICS_HEIGHT, 1000, 200, "Snake", true);

	// Constantes de classe
	public static int fps = 10;
	public int level = 0;
	public int aff = 0;

	// Savoir si on est train de jouer ou non
	boolean play = false;

	Scanner scan = new Scanner(System.in);

	// Constructeur
	public Snake() {
		initialisationSnake(6);
		
	}
	
	//Cr�ation d'un snake de longueur 6
	public void initialisationSnake(int size){
		this.position[posx][posy] = 1;
		for (int i = 2; i <= size; i++) {

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
	}

	// methode d'affichage du menu
	public void updateGraphicsViewMenu() {
		display.clear();
		display.drawFancyString(GRAPHICS_WIDTH / 5, GRAPHICS_HEIGHT / 4, "SNAKE by Philippine & Mathieu", Color.BLACK,
				30);
		display.drawString(GRAPHICS_WIDTH / 3 + 100, GRAPHICS_HEIGHT / 2, "1) Level 1", Color.BLACK, 20);
		display.drawString(GRAPHICS_WIDTH / 3 + 100, GRAPHICS_HEIGHT / 2 + 50, "2) Level 2", Color.BLACK, 20);
		display.drawString(GRAPHICS_WIDTH / 3 + 100, GRAPHICS_HEIGHT / 2 + 100, "3) Level 3", Color.BLACK, 20);
		display.drawString(GRAPHICS_WIDTH / 3 + 50, GRAPHICS_HEIGHT / 2 + 150, "Please select one Level : ",
				Color.BLACK, 20);
		level = Dialogs.getInt("Your choice:") - '0';

		display.clear();
		display.drawFancyString(GRAPHICS_WIDTH / 5, GRAPHICS_HEIGHT / 4, "SNAKE by Philippine & Mathieu", Color.BLACK,
				30);
		display.drawString(GRAPHICS_WIDTH / 3 + 100, GRAPHICS_HEIGHT / 2, "1) Affichage couleurs", Color.BLACK, 20);
		display.drawString(GRAPHICS_WIDTH / 3 + 100, GRAPHICS_HEIGHT / 2 + 50, "2) Affichage retro grand", Color.BLACK,
				20);
		display.drawString(GRAPHICS_WIDTH / 3 + 100, GRAPHICS_HEIGHT / 2 + 100, "3) Affichage retro petit",
				Color.BLACK, 20);
		display.drawString(GRAPHICS_WIDTH / 3 + 50, GRAPHICS_HEIGHT / 2 + 150, "Please select one Level : ",
				Color.BLACK, 20);
		aff = Dialogs.getInt("Your choice:") - '0';
		
		fps=10;
		apple();
		begin = System.currentTimeMillis();
	}


	// methode d'affichage
	public void updateGraphicsViewGame() {

		synchronized (display.frontBuffer) {
			display.clear();
			long time = (end - begin) / 1000;
			
			switch (level) {
			case 1:
				// simple map sans obstacle, vitesse augmente chaque 3 pommes
				break;
			case 2:
				// map avec mur sur le pourtour, vitesse constante
				for (int i = 0; i < position.length; i++) {
					for (int j = 0; j < position[i].length; j++) {
						if (i == 0 || j == 0 || i == position.length - 1 || j == position[i].length - 1) {
							position[i][j] = -2;
						}
					}
				}
				break;
			case 3:
				// map avec plusieurs murs,aussi sur le milieu, vitesse constante
				for (int i = 0; i < position.length; i++) {
					for (int j = 0; j < position[i].length; j++) {
						if ((i == 0 || i == position.length - 1) && position[i][j] == 0) {
							position[i][j] = -2;
						} else if ((j == 7 && i <= 16) || (j == 24 && i > 16) && (position[i][j] == 0)) {
							position[i][j] = -2;
						} else if ((i == 8 && j < 16 && j > 7) || (i == 23 && j < 25 && j > 14) && (position[i][j] == 0)) {
							position[i][j] = -2;
						}
					}

				}

			}
			switch (aff) {
			// affichage 1=cases couleurs, 2=original snake grand
			// format, 3=original snake
			// si aff 1 fenetre 320x320 pixels
			// si aff 2 fenetre 832x832 pixels
			// si aff 3 fenetre 416x416 pixels
			case 1:
				GRAPHICS_WIDTH = 320;
				GRAPHICS_HEIGHT = 320;
				break;
			case 2:
				GRAPHICS_WIDTH = 832;
				GRAPHICS_HEIGHT = 832;
				break;
			case 3:
				GRAPHICS_WIDTH = 416;
				GRAPHICS_HEIGHT = 416;
				break;

			}
			
		
			for (int i = 0; i < position.length; i++) {
				for (int j = 0; j < position.length; j++) {
					// les valeurs des cases voisines
					int value_up = 0;
					int value_dw = 0;
					int value_rgt = 0;
					int value_lft = 0;
					int value_case = position[i][j];

					if (i == 0 && j == 0) {
						value_up = position[i][31];
						value_dw = position[i][j + 1];
						value_rgt = position[i + 1][j];
						value_lft = position[31][j];
					} else if (i == 0 && j > 0 && j < 31) {
						value_up = position[i][j - 1];
						value_dw = position[i][j + 1];
						value_rgt = position[i + 1][j];
						value_lft = position[31][j];
					} else if (i == 0 && j == 31) {
						value_up = position[i][j - 1];
						value_dw = position[i][0];
						value_rgt = position[i + 1][j];
						value_lft = position[31][j];
					} else if (i > 0 && i < 31 && j == 0) {
						value_up = position[i][31];
						value_dw = position[i][j + 1];
						value_rgt = position[i + 1][j];
						value_lft = position[i - 1][j];
					} else if (i == 31 && j == 0) {
						value_up = position[i][31];
						value_dw = position[i][j + 1];
						value_rgt = position[0][j];
						value_lft = position[i - 1][j];
					} else if (i > 0 && i < 31 && j > 0 && j < 31) {
						value_up = position[i][j - 1];
						value_dw = position[i][j + 1];
						value_rgt = position[i + 1][j];
						value_lft = position[i - 1][j];
					} else if (i == 31 && j > 0 && j < 31) {
						value_up = position[i][j - 1];
						value_dw = position[i][j + 1];
						value_rgt = position[0][j];
						value_lft = position[i - 1][j];
					} else if (i > 0 && i < 31 && j == 31) {
						value_up = position[i][j - 1];
						value_dw = position[i][0];
						value_rgt = position[i + 1][j];
						value_lft = position[i - 1][j];
					} else if (i == 31 && j == 31) {
						value_up = position[i][j - 1];
						value_dw = position[i][0];
						value_rgt = position[0][j];
						value_lft = position[i - 1][j];
					}

					switch (value_case) {
					case -2:
						// mur
						if (aff == 1)
							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, rock);
						if (aff == 2)
							display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, mur);
						if (aff == 3)
							display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, mur);
						break;

					case -1:
						// pomme
						if (aff == 1)
							display.drawTransformedPicture(i * 10 + 5, j * 10, 0.0, 0.25, apple);
						if (aff == 2)
							display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, p);
						if (aff == 3)
							display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, p);
						break;

					case 0:
						// vide
						if (aff == 1)
							display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, vide);
						if (aff == 2)
							display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, v);
						if (aff == 3)
							display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, v);
						break;

					case 1:
						if (value_lft == 2) {
							// t�te normal corp gauche
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, head);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, tcd);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, tcd);
						}
						if (value_rgt == 2) {
							// t�te normal corp droite
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, head);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, tcg);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, tcg);
						}
						if (value_dw == 2) {
							// t�te normal corp bas
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, head);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, tcb);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, tcb);
						}
						if (value_up == 2) {
							// t�te normal corp haut
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, head);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, tch);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, tch);
						}
						if (value_lft == 2 && value_rgt == -1) {
							// t�te gueule ouverte corp gauche
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, head);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, tnd);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, tnd);
						}
						if (value_lft == -1 && value_rgt == 2) {
							// t�te gueule ouverte corp droite
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, head);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, tng);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, tng);
						}
						if (value_up == -1 && value_dw == 2) {
							// t�te gueule ouverte corp bas
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, head);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, tnh);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, tnh);
						}
						if (value_dw == -1 && value_up == 2) {
							// t�te gueule ouverte corp haut
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, head);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, tnb);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, tnb);
						}

						break;

					default:
						if ((value_rgt == value_case + 1 || value_rgt == -2) && value_lft == value_case - 1) {
							// corp gauche à droite
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, body);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, cdtd);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, cdtd);
						}
						if ((value_lft == value_case + 1 || value_lft == -2) && value_rgt == value_case - 1) {
							// corp droite à gauche
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, body);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, cdtg);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, cdtg);

						}
						if ((value_up == value_case - 1 || (value_up == -2) && value_dw == value_case + 1)) {
							// corp bas à haut
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, body);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, cdth);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, cdth);

						}
						if ((value_dw == value_case - 1 || (value_dw == -2) && value_up == value_case + 1)) {
							// corp haut à bas
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, body);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, cdtb);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, cdtb);
						}
						if (value_lft == value_case + 1 && value_up == value_case - 1
								|| value_lft == value_case - 1 && value_up == value_case + 1) {
							// coude gauche-haut
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, coude);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, cgh);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, cgh);
						}
						if (value_up == value_case + 1 && value_rgt == value_case - 1
								|| value_up == value_case - 1 && value_rgt == value_case + 1) {
							// coude haut-droite
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, coude);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, chd);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, chd);
						}
						if (value_lft == value_case + 1 && value_dw == value_case - 1
								|| value_lft == value_case - 1 && value_dw == value_case + 1) {
							// coude gauche-bas
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, coude);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, cgb);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, cgb);
						}
						if (value_rgt == value_case + 1 && value_dw == value_case - 1
								|| value_rgt == value_case - 1 && value_dw == value_case + 1) {
							// coude droite-bas
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, coude);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, cbd);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, cbd);
						}
						if (value_case == length && value_lft == length - 1
								|| value_case == length && value_lft == length - 1 && value_rgt == 0 && value_lft > 1) {
							// queue corp gauche
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, queue);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, qcg);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, qcg);
						}
						if (value_case == length && value_rgt == length - 1
								|| value_case == length && value_lft == length - 1 && value_lft == 0 && value_rgt > 1) {
							// queue corp droite
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, queue);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, qcd);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, qcd);
						}
						if (value_case == length && value_up == length - 1
								|| value_case == length && value_lft == length - 1 && value_dw == 0 && value_up > 1) {
							// queue corp haut
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, queue);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, qch);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, qch);
						}
						if (value_case == length && value_dw == length - 1
								|| value_case == length && value_lft == length - 1 && value_up == 0 && value_dw > 1) {
							// queue corp bas
							if (aff == 1)
								display.drawTransformedPicture(i * 10 + 5, j * 10 + 5, 0.0, 0.25, queue);
							if (aff == 2)
								display.drawTransformedPicture(i * 26 + 13, j * 26 + 13, 0.0, 0.4, qcb);
							if (aff == 3)
								display.drawTransformedPicture(i * 13 + 7, j * 13 + 7, 0.0, 0.2, qcb);
						}

						break;
					}

				}
				switch(level){
				case 1:
					display.drawString(GRAPHICS_WIDTH / 2 - 20, 60, "Score: " + score1 , Color.BLACK, 25);
					break;
				case 2:
					display.drawString(GRAPHICS_WIDTH / 2 - 20, 60, "Score: " + score2 , Color.BLACK, 25);
					break;
				case 3:
					display.drawString(GRAPHICS_WIDTH / 2 - 20, 60, "Score: " + score3, Color.BLACK, 25);
					break;
				}
				display.drawString(30, GRAPHICS_HEIGHT - 40, "Timer: " + time + " sec", Color.BLACK, 25);
				display.drawString(GRAPHICS_WIDTH - 120, GRAPHICS_HEIGHT - 40, "FPS: " + fps, Color.BLACK, 25);
			}
		}

		display.syncGameLogic(fps);

	}

	// Methode qui lit les fleches
	public void direction() {
		display.setKeyManager(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT && dirSnake != Direction.LEFT) {
					dirSnake = Direction.RIGHT;

				} else if (e.getKeyCode() == KeyEvent.VK_LEFT && dirSnake != Direction.RIGHT) {
					dirSnake = Direction.LEFT;

				} else if (e.getKeyCode() == KeyEvent.VK_UP && dirSnake != Direction.DOWN) {
					dirSnake = Direction.UP;

				} else if (e.getKeyCode() == KeyEvent.VK_DOWN && dirSnake != Direction.UP) {
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
			if(level == 1){
				score1++;
			}else if(level == 2){
				score2++;
			}else if(level == 3){
				score3++;
			}
			try {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("1190.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
			} catch (Exception ex) {
				ex.printStackTrace();
				;
			}
		} else if (position[headX][headY] == 0) {
			position[headX][headY] = 1;
			length++;
		} else {
			play = false;
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

		if (score1 % 3 == 0 && level == 1 && score1 != 0) {
			fps += 2;
		}
		
		if(score2 % 5 == 0 && level == 2 && score2 != 0){
			fps += 3;
		}
		
		

	}

	// Methode s'il y a un echec
	public void gameover() {
		play = false;
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("1234.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (Exception ex) {
			ex.printStackTrace();
			;
		}
		display.clear(Color.WHITE);
		display.drawString(GRAPHICS_WIDTH / 3, GRAPHICS_HEIGHT / 3, "Sorry, you lose!", Color.RED, 40);
		display.drawString(GRAPHICS_WIDTH / 3, GRAPHICS_HEIGHT / 2 + 90, "Your score is : " + (score1+score2+score3), Color.BLUE, 40);

	}

	public boolean replay() {
		boolean replay = false;
		char reponse = Character.toLowerCase(Dialogs.getChar("Do you want to play again?(y/n)"));
		if (reponse == 'y') {
			play = true;
			display.clear();
			return true;
		} else {
			display.clear();
			display.drawString(GRAPHICS_WIDTH / 2, GRAPHICS_HEIGHT / 2, "Thank's, see you soon!", Color.BLACK, 40);
			System.exit(reponse);
		}
		return replay;
	}

	public void restart() {

	}
	
	public void level1(){
		end = System.currentTimeMillis();

		move(dirSnake);
		if (nbApple == 0) {

			apple();

		}
		updateGraphicsViewGame();
	}
	
	public void level2(){
		end = System.currentTimeMillis();

		move(dirSnake);
		if (nbApple == 0) {

			apple();

		}
		updateGraphicsViewGame();
	}
	
	public void level3(){
		end = System.currentTimeMillis();

		move(dirSnake);
		if (nbApple == 0) {

			apple();

		}
		updateGraphicsViewGame();
	}

	public void changeLevel(){
		if(score1 == 30 && level == 1){
			
			for(int i = 0; i<position.length; i++){
				for(int j = 0; j<position[i].length; j++){
					position[i][j] = 0;
				}
			}
			nbApple = 0;
			fps = 10;
			length = 6;
			initialisationSnake(6);
			level++;
		}else if(score2 == 40 && level ==2){
			for(int i = 0; i<position.length; i++){
				for(int j = 0; j<position[i].length; j++){
					position[i][j] = 0;
				}
			}
			nbApple = 0;
			fps = 10;
			length = 6;
			initialisationSnake(6);
			level++;
		}
	}
	public void play() {
		updateGraphicsViewMenu();
		direction();
		while (play) {
			changeLevel();

			switch(level){
			case 1:
				level1();
				break;
			case 2:
				level2();
				break;
			case 3:
				level3();
				break;
			}

		}
		gameover();

	}

}
