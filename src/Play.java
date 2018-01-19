import hevs.graphics.FunGraphics;
import hevs.utils.Input;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Play {
	public static void main(String[] args) {
		Snake snake = new Snake();
		snake.play();
		
		while(snake.replay()){
			snake = new Snake();
			snake.play();
			
		}
			
		}
	}

