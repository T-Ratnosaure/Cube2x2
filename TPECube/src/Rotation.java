import java.util.ArrayList;

public class Rotation {
	public ArrayList<Integer> position;
	public ArrayList<Integer> orientation;
	
	public Rotation(int... pos) {
		this.position = new ArrayList<Integer>();
		this.orientation=new ArrayList<Integer>();
		for(int i=0; i< 14; i++) {
			this.position.set(i,pos[i]);
			this.orientation.set(i+7,pos[i+7]);
		}

		
	}
	
	public Rotation permProd(Rotation B) {
		Rotation t = new Rotation(0,0,0,0,0,0,0,0,0,0,0,0,0,0);
		for(int i=0;i<7;i++) {
			t.position.set(i, B.position.get(this.position.get(i)-1));
			t.orientation.set(i, (B.orientation.get(this.position.get(i)-1)+B.orientation.get(i))%3);
		}
		return t;
	}
	
	
}
