
public class Dessin {

	public void updateGraphicsView(){
		
		synchronized(Level.display.frontBuffer){
			Level.display.clear();
			Level.display.drawString(20, 20, "Timer: ");

			for (int i = 0; i < Level.GRAPHICS_WIDTH; i += 10) {
				for (int j = 0; j < Level.GRAPHICS_HEIGHT; j += 10) {

					if (position[i][j]==0) {
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, v);
						break;}
					else if (position[i][j]==-1){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, p);
						break;
					}else if (position[i][j]==-2 && (position[i][j-1]==-2 || position[i][j+1]==-2){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, mgd);
						break;}
					else if (position[i][j]==-2 && (position[i-1][j]==-2 || position[i+1][j]==-2){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, mhb);
						break;}
					else if (position[i][j]==1&&position[i][j-1]>0&&position[i][j+1]==-1){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, tnd);
						break;}
					else if (position[i][j]==1&&position[i][j+1]>0&&position[i][j-1]==-1){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, tng);
						break;}
					else if (position[i][j]==1&&position[i-1][j]>0&&position[i+1][j]==-1){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, tnb);
						break;}
					else if (position[i][j]==1&&position[i+1][j]>0&&position[i-1][j]==-1){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, tnh);
						break;}					
					else if (position[i][j]==1&&position[i][j-1]>0&&position[i][j+1]==0){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, tcd);
						break;}
					else if (position[i][j]==1&&position[i][j+1]>0&&position[i][j-1]==0){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, tcg);
						break;}
					else if (position[i][j]==1&&position[i-1][j]>0&&position[i+1][j]==0){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, tch);
						break;}
					else if (position[i][j]==1&&position[i+1][j]>0&&position[i-1][j]==0){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, tcb);
						break;}
					else if (position[i][j]>1&&position[i][j-1]>position[i][j+1]){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, cdtd);
						break;}
					else if (position[i][j]>1&&position[i][j-1]<position[i][j+1]){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, cdtg);
						break;}
					else if (position[i][j]>1&&position[i-1][j]>position[i+1][j]){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, cdtb);
						break;}
					else if (position[i][j]>1&&position[i-1][j]<position[i+1][j]){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, cdth);
						break;}
					else if (position[i][j]>1 && position[i][j-1]>0 && position[i+1][j]>0){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, cgb);
						break;}
					else if (position[i][j]>1 && position[i][j+1]>0 && position[i+1][j]>0){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, cbd);
						break;}
					else if (position[i][j]>1 && position[i][j-1]>0 && position[i-1][j]>0){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, cgh);
						break;}
					else if (position[i][j]>1 && position[i][j+1]>0 && position[i-1][j]>0){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, chd);
						break;}
					else if (position[i][j]==length && position[i][j-1]==length-1){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, qcd);
						break;}
					else if (position[i][j]==length && position[i][j+1]==length-1){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, qcg);
						break;}
					else if (position[i][j]==length && position[i-1][j]==length-1){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, qch);
						break;}
					else if (position[i][j]==length && position[i+1][j]==length-1){
						Level.display.drawTransformedPicture(i, j, 0.0, 0.25, qcb);
						break;}
					
						
						
					
					}
				}
			}
		}

	Level.display.syncGameLogic(Level.fps);

}}
