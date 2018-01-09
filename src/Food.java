import java.awt.Color;

public class Food {
	int size;
	Color color;
	static int[][] posFood = new int[64][64];
	boolean isEating = false;

	static void createFood (boolean a){
		if (a==false){
			posFood[(int)Math.random()*64][(int)Math.random()*64];
			}
		}

	public Food(int size, Color color) {
		this.size = size;
		this.color = color;
	}

	public void foodEating(boolean b) {
		isEating = b;
	}

	public byte[][] getPosFood() {
		return posFood;
	}

}
