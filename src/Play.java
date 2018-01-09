import hevs.graphics.FunGraphics;

public class Play {
	public static void main(String[] args) {
		Snake snake = new Snake(6, Snake.Direction.LEFT);
		snake.move();
		
	}
}
