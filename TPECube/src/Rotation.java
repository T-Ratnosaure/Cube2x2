import java.util.ArrayList;
import java.util.Arrays;
/**
 * Classe présentant une rotation ( modèle mathématique de la permutation ) du Rubik's Cube
 * @author Léandre Adam
 *
 */
public class Rotation {
	/**
	 * liste des changements de position qu'effectue cette rotation
	 */
	private ArrayList<Integer> position;
	/**
	 * liste des changements d'orientation qu'effectue cette rotation
	 */
	private ArrayList<Integer> orientation;
	/**
	 * taille d'une rotation ( nombre de Cubies qu'elle affecte )
	 */
	final public int TAILLE=7;
	/**
	 * Constructeur de la Rotation
	 * @param pos comprenant 14 valeurs numériques : les 7 premières représentant les positions et les 7 suivantes sont les orientations
	 */
	public Rotation(int... pos) {
		this.position = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0));
		this.orientation=new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0));
		for(int i=0; i<TAILLE; i++) {
			this.position.set(i,pos[i]);
			this.orientation.set(i,pos[i+7]);
		}

		
	}
	/**
	 * calcule le produit B°A
	 * @param B une Rotation
	 * @return B°this
	 */
	public Rotation permProd(Rotation B) {
		Rotation t = new Rotation(0,0,0,0,0,0,0,0,0,0,0,0,0,0);
		for(int i=0;i<TAILLE;i++) {
			t.position.set(i, B.position.get(this.position.get(i)-1));
			t.orientation.set(i, (B.orientation.get(this.position.get(i)-1)+this.orientation.get(i))%3);
		}
		return t;
	}
	/**
	 * Calcule la permutation inverse de this
	 * @return A^{-1}
	 */
	public Rotation permInv() {
		Rotation t = new Rotation(0,0,0,0,0,0,0,0,0,0,0,0,0,0);
		for(int i=0;i<TAILLE;i++) {
			t.position.set(this.position.get(i)-1, i+1);
			t.orientation.set(this.position.get(i)-1, (3-this.orientation.get(i))%3);
		}
		return t;
	}
	/**
	 * Renvoie la list de la position
	 * @return position
	 */
	public ArrayList<Integer> getPosition(){
		return this.position;
	}
	/**
	 * Renvoie la liste de l'orientation
	 * @return orientation
	 */
	public ArrayList<Integer> getOrientation(){
		return this.orientation;
	}
	
	
}
