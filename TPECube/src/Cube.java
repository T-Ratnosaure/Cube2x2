/**
 * Classe représentant le Cube de Rubik
 * @author Léandre Adam
 * 
 */
public class Cube {
	
	final public static Integer[] POSITIONS_INITIALES = {0,1,2,3,4,5,6};
	final public static int TAILLE=7;
	
	final public Cubie[] cubies = new Cubie[TAILLE];
	@SuppressWarnings("unused")
	private boolean solved;
	
	/**
	 * Constructeur du Cube
	 * @param pos : tableau des positions des 7 cubies
	 * @param ori : tableau des orientations des 7 cubies
	 */
	public Cube(Integer[] posAc, Integer[] ori) {
		for (int i = 0; i<TAILLE; i++ ) {
			cubies[i] = new Cubie(POSITIONS_INITIALES[i], posAc[i], ori[i]); 
		}
	}
	/**
	 * Méthode appliquant une permutation sur le Cube this
	 * @param w la rotation à appliquer
	 */
	public void appliquePerm(Rotation w) {
		for (int i = 0; i<TAILLE; i++) {
			int posBeforeRotation = this.cubies[i].getPos();
			int oriBeforeRotation = this.cubies[i].getOr();
			int oriAfterRotation = w.getOrientation().get(posBeforeRotation);
			int posAfterRotation = w.getPosition().get(posBeforeRotation);
			
			this.cubies[i].setOr((oriAfterRotation + oriBeforeRotation)%3);
			this.cubies[i].setPos(posAfterRotation);
		}
	}
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i<TAILLE; i++) {
			result += "Le cubie " + i + " est en position " + this.cubies[i].getPos() + " et d'orientation " + this.cubies[i].getOr() + "\n";
		}
		return result;
	}
}
