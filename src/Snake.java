
public class Snake {
	enum Direction {
		LEFT, RIGHT, UP, DOWN
	};

	int length = 3;

	Direction dirSnake = Direction.LEFT;
	
	int position[][]=new int[64][64];
	
	int posHeadX=32;
	int posHeadY=32;
	
	position[posX][posY]=1;
	
	for (int i=0;i<length;i++){
		int posBodyX;
		int posBodyY;
		switch (dirSnake){
		case LEFT:
			posBodyX=posHeadX+1;
			posBodyY=posHeadY;
			
			break;
		case RIGHT:
			posBodyX=posHeadX-1;
			posBodyY=posHeadY;
			break;
		case UP:
			posBodyX=posHeadX;
			posBodyY=posHeadY+1;
			break;
		case DOWN:
			posBodyX=posHeadX;
			posBodyY=posHeadY-1;
			break;
		}
		position[posBodyX][posBodyY]=i+2;
		
	}


	public Snake() {
		body = new Body(3);

	}
}
