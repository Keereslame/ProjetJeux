import hevs.graphics.FunGraphics;
import hevs.utils.Input;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Play {
	public static void main(String[] args) {
		Snake snake = new Snake(6, Snake.Direction.LEFT);
		while(true){
			
				snake.direction();
		}
	}
}
