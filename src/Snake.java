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
	Direction dirSnake = Direction.LEFT;
	int posx = 32;
	int posy = 32;
	int position[][] = new int[64][64];

	// Constructeur
	public Snake(int length, Direction direction) {
		new Display();
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

	}

	//Méthode qui lit les flèches
	public void direction() {
		Display.display.setKeyManager(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if(dirSnake != Direction.LEFT)
						dirSnake = Direction.RIGHT;
					else
						gameover();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if(dirSnake != Direction.RIGHT)
						dirSnake = Direction.LEFT;
					else
						gameover();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					if(dirSnake != Direction.DOWN)
						dirSnake = Direction.UP;
					else
						gameover();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if(dirSnake != Direction.UP)
						dirSnake = Direction.DOWN;
					else
						gameover();
				}
			}

		});

		while (true) {
			move(dirSnake);
			Display.display.clear();


			for(int i=0;i< Display.GRAPHICS_WIDTH;i+=10){
				for(int j=0;j < Display.GRAPHICS_HEIGHT; j+=10){
					if(position[i/10][j/10] == 1){
						Display.display.setColor(Color.RED);
						Display.display.drawCircle(i, j, 5);
					}else if(position[i/10][j/10] > 1){
						Display.display.setColor(Color.BLACK);
						Display.display.drawCircle(i, j, 5);
					}else if(position[i/10][j/10] == -1){
						Display.display.setColor(Color.BLUE);
						Display.display.drawRect(i, j, 4, 4);
					}else if(position[i/10][j/10] == -2){
						Display.display.setColor(Color.ORANGE);
						Display.display.drawRect(i, j, 4, 4);
					}
				}
			}



				Display.display.syncGameLogic(10);

				System.out.println(dirSnake);
				for (int j = 0; j < position.length; j++) {
					for (int k = 0; k < position[j].length; k++) {
						System.out.print(position[j][k]);
					}
					System.out.println("");
				}
			
		}
	}
	
	//Méthode pour bouger le snake selon la direction
	public void move(Direction d) {
		
		switch (d) {
		case LEFT:
			for(int i = 0; i < position.length; i++){
				for(int j = 0; j < position[i].length; j++){
					if(position[i][j] > 0){
						position [i][j] += 1;
					}
				}
			}
			for(int i = 0; i < position.length-1; i++){
				for(int j = 0; j < position[i].length-1; j++){
					if(position[i][j] == 2){
						position[i-1][j] = 1;
					}
				}
			}
		
			break;
		case RIGHT:
			for(int i = 0 ; i < position.length; i++){
				for(int j = 0; j < position[i].length; j++){
					if(position[i][j] > 0){
						position [i][j] += 1;
					}
				}
			}
			for(int i = 0; i < position.length-1; i++){
				for(int j = 0; j < position[i].length-1; j++){
					
					if(position[i][j] == 2){
						position[i+1][j] = 1;
					}
				}
			}
			
			break;
		case UP:
			for(int i = 0; i < position.length; i++){
				for(int j = 0;j < position[i].length; j++){
					if(position[i][j] > 0){
						position [i][j] += 1;
					}
				}
			}
			for(int i = 0; i < position.length-1; i++){
				for(int j = 0; j < position[i].length-1; j++){
					
					if(position[i][j] == 2){
						position[i][j-1] = 1;
					}
				}
			}

			break;
		case DOWN:
			for(int i = 0; i < position.length; i++){
				for(int j = 0; j < position[i].length; j++){
					if(position[i][j] > 0){
						position [i][j] += 1;
					}
				}
			}
			for(int i = 0; i < position.length-1; i++){
				for(int j = 0; j < position[i].length-1; j++){
					
					if(position[i][j]==2){
						position[i][j+1] = 1;
					}
				}
			}

			break;
		default:
			break;
		}
		for(int i=0;i<position.length;i++){
			for(int j=0;j<position[i].length;j++){
				if (position[i][j]>length)
					position[i][j]=0;
			}
		}
	}
	
	//Méthode si'il y a un échec
	public void gameover(){
		System.out.println("PERDU");
		System.exit(1);
	}

}
