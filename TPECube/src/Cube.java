import java.util.ArrayList;

/**
 * Classe représentant le Cube de Rubik
 * @author Léandre Adam
 * 
 */
public class Cube {
	
	/**
	 * 2 constantes : Un tableau des positions de chaque Cubie dans le Cube résolu; la taille du Cube = nombre de Cubies)
	 */
	final public static Integer[] POSITIONS_INITIALES = {0,1,2,3,4,5,6};
	final public static int TAILLE=7;
	
	/**
	 * 9 constantes Rotations, toutes les rotations possibles lors du jeu
	 */	
	@SuppressWarnings("unused")
	final public static Rotation R = new Rotation(new Integer[]{4,1,2,0,6,5,3}, new Integer[]{1,0,0,2,2,0,1});
	@SuppressWarnings("unused")
	final public static Rotation F = new Rotation(new Integer[]{3,0,1,2,4,5,6},new Integer[]{2,1,2,1,0,0,0});
	@SuppressWarnings("unused")
	final public static Rotation U = new Rotation(new Integer[]{1,5,2,3,0,4,6},new Integer[]{0,0,0,0,0,0,0});
	@SuppressWarnings("unused")
	final public static Rotation Fprime = F.permInv();
    @SuppressWarnings("unused")
	final public static Rotation Uprime = U.permInv();
	@SuppressWarnings("unused")
	final public static Rotation Rprime = R.permInv();
	@SuppressWarnings("unused")
	final public static Rotation R2 = R.permProd(R);
	@SuppressWarnings("unused")
	final public static Rotation U2 = U.permProd(U);
	@SuppressWarnings("unused")
	final public static Rotation F2 = F.permProd(F);
	
	/**
	 * Tableau des 7 Cubies du Cube
	 */
	final public Cubie[] cubies = new Cubie[TAILLE];
	@SuppressWarnings("unused")
	private boolean solved;
	
	/**
	 * Constructeur du Cube
	 * @param pos : tableau des positions des 7 Cubies
	 * @param ori : tableau des orientations des 7 Cubies
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
	
	/**
	 * Échange les deux positions des Cubies 0 et 4
	 * @param tableau des permutations déjà effectuées (vide au début éventuellement)
	 * @return tableau de toutes les permutations
	 */
	public ArrayList<Rotation> transposition(ArrayList<Rotation> doneRotation) {
			Rotation[] perm = new Rotation[] {R,U2,Rprime,Uprime,R,U2,Rprime,F,Rprime,Fprime,R};
			for(int i=0;i<perm.length;i++) {
				this.appliquePerm(perm[i]);
				doneRotation.add(perm[i]);
			}
			return doneRotation;
	}
	
	/**
	 * Renvoie une ligne décrivant chacune un Cubie (son numéro, sa position actuelle et son orientation actuelle)
	 */
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i<TAILLE; i++) {
			result += "Le cubie " + i + " est en position " + this.cubies[i].getPos() + " et d'orientation " + this.cubies[i].getOr() + "\n";
		}
		return result;
	}
}