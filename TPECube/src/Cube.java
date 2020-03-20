/**
 * Classe représentant le Cube de Rubik
 * @author Léandre Adam
 * 
 */
public class Cube {
	private Cubie un;
	private Cubie deux;
	private Cubie trois;
	private Cubie quatre;
	private Cubie cinq;
	private Cubie six;
	private Cubie sept;
	@SuppressWarnings("unused")
	private boolean solved;
	
	/**
	 * Constructeur du Cube
	 * @param pos : comprenant 14 valeurs numériques : les 7 premières représentant les positions et les 7 suivantes sont les orientations
	 */
	public Cube(int... pos) {
		un = new Cubie(1,pos[0],pos[7]);
		deux = new Cubie(2,pos[1],pos[8]);
		trois=new Cubie(3,pos[2],pos[9]);
		quatre=new Cubie(4,pos[3],pos[10]);
		cinq=new Cubie(5,pos[4],pos[11]);
		six=new Cubie(6,pos[5],pos[12]);
		sept=new Cubie(7, pos[6],pos[13]);
	}
	/**
	 * Méthode appliquant une permutation sur le Cube this
	 * @param w la rotation à appliquer
	 */
	public void appliquePerm(Rotation w) {
		this.un.setOr((w.getOrientation().get(this.un.getPos()-1)+this.un.getOr())%3);
		this.deux.setOr((w.getOrientation().get(this.deux.getPos()-1)+this.deux.getOr())%3);
		this.trois.setOr((w.getOrientation().get(this.trois.getPos()-1)+this.trois.getOr())%3);
		this.quatre.setOr((w.getOrientation().get(this.quatre.getPos()-1)+this.quatre.getOr())%3);
		this.cinq.setOr((w.getOrientation().get(this.cinq.getPos()-1)+this.cinq.getOr())%3);
		this.six.setOr((w.getOrientation().get(this.six.getPos()-1)+this.six.getOr())%3);
		this.sept.setOr((w.getOrientation().get(this.sept.getPos()-1)+this.sept.getOr())%3);
		
		
		this.un.setPos(w.getPosition().get(this.un.getPos()-1));
		this.deux.setPos(w.getPosition().get(this.deux.getPos()-1));
		this.trois.setPos(w.getPosition().get(this.trois.getPos()-1));
		this.quatre.setPos(w.getPosition().get(this.quatre.getPos()-1));
		this.cinq.setPos(w.getPosition().get(this.cinq.getPos()-1));
		this.six.setPos(w.getPosition().get(this.six.getPos()-1));
		this.sept.setPos(w.getPosition().get(this.sept.getPos()-1));	
	}
	@Override
	public String toString() {
		return "Le cubie 1 est en position "+this.un.getPos()+ " et d'orientation "+this.un.getOr()+"\n"+"Le cubie 2 est en position "+this.deux.getPos()+ " et d'orientation "+this.deux.getOr()+"\n"+"Le cubie 3 est en position "+this.trois.getPos()+ " et d'orientation "+this.trois.getOr()+"\n"+"Le cubie 4 est en position "+this.quatre.getPos()+ " et d'orientation "+this.quatre.getOr()+"\n"+"Le cubie 5 est en position "+this.cinq.getPos()+ " et d'orientation "+this.cinq.getOr()+"\n"+"Le cubie 6 est en position "+this.six.getPos()+ " et d'orientation "+this.six.getOr()+"\n"+"Le cubie 7 est en position "+this.sept.getPos()+ " et d'orientation "+this.sept.getOr()+"\n";
	}
}
