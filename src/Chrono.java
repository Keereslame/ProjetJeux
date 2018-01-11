
public class Chrono {
	
	private long Start = 0;
	private long Stop = 0;
	private long duree = 0;
	
	public void start(){
		Start = System.currentTimeMillis();
		Stop = 0;
	}
	
	public void stop(){
		if(Start==0) {return;}
		Stop=System.currentTimeMillis();
		duree=Stop - Start;
    }
	
	public long getDureeSec(){
		return duree/1000;
    }
	
	public String getDureeTxt(){
		return timeToHMS(getDureeSec());
	}
	
	public static String timeToHMS(long tempsS) {

		// IN : (long) temps en secondes
		// OUT : (String) temps au format texte : "1 h 26 min 3 s"

		int h = (int) (tempsS / 3600);
		int m = (int) ((tempsS % 3600) / 60);
		int s = (int) (tempsS % 60);

		String r="";

		if(h>0) {r+=h+" h ";}
		if(m>0) {r+=m+" min ";}
		if(s>0) {r+=s+" s";}
		if(h<=0 && m<=0 && s<=0) {r="0 s";}

		return r;
	}
    

}
