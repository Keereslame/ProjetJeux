import hevs.graphics.FunGraphics;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Snake {
	enum Direction {
		LEFT, RIGHT, UP, DOWN
	};
	//Attributs de classe
	int length;
	Direction dirSnake = Direction.LEFT;
	int posx = 32;
	int posy = 32;
	int position[][] = new int[64][64];
	
	
	
	//Constructeur	
	public Snake(int length, Direction direction){
		new Display();
		this.length = length;
		this.dirSnake = direction;
		this.position[posx][posy] = 1;
		for(int i = 2; i <= length; i++){
			
			if(dirSnake == Direction.LEFT){
				position[posx][posy+(i-1)] = i;
				
			}else if(dirSnake == Direction.DOWN){
				position[posx-(i-1)][posy] = i;
				
			}else if(dirSnake == Direction.RIGHT){
				position[posx][posy-(i-1)] = i;
				
			}else{
				position[posx+(i+1)][posy] = i;
				
			}
		}
		
		for(int j=0; j < position.length; j++){
			for(int k=0; k < position[j].length; k++){
				System.out.print(position[j][k]);
			}
			System.out.println("");
		}
	}
	
	public void move(){
		Display.display.setKeyManager(new KeyAdapter(){
			
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					dirSnake = Direction.RIGHT;
				}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
					dirSnake = Direction.LEFT;
				}else if(e.getKeyCode() == KeyEvent.VK_UP){
					dirSnake = Direction.UP;
				}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
					dirSnake = Direction.DOWN;
				}
			}
			
		});
		
		while(true){
			Display.display.clear();
			
			Display.display.setColor(Color.RED);
			Display.display.drawCircle(posx*10, posy*10, 5);
			
			Display.display.syncGameLogic(60);
		}
	}
	
	

}
