import java.util.Arrays;
import java.util.List;
/**
 * Classe présentant une rotation ( modèle mathématique de la permutation ) du Rubik's Cube
 * @author Léandre Adam
 *
 */
public class Rotation {
	/**
	 * liste des changements de position qu'effectue cette rotation
	 */
	private List<Integer> position;
	/**
	 * liste des changements d'orientation qu'effectue cette rotation
	 */
	private List<Integer> orientation;
	/**
	 * Constructeur de la Rotation
	 * @param pos : tableau des positions des 7 cubies
	 * @param ori : tableau des orientations des 7 cubies
	 */

	public Rotation(Integer[] pos,Integer[] ori) {
		this.position = Arrays.asList(pos);
		this.orientation= Arrays.asList(ori);		
	}
	
	
	/**
	 * calcule le produit B°this (d'abord this puis B)
	 * @param B une Rotation
	 * @return B°this
	 */
	public Rotation permProd(Rotation B) {
		Integer[] pos = new Integer[7];
		Integer[] ori = new Integer[7];
		for(int i=0;i<Cube.TAILLE;i++) {
			pos[i]=B.position.get(this.position.get(i));
			ori[i]=(B.orientation.get(this.position.get(i))+this.orientation.get(i))%3;
		}
		Rotation productRotation = new Rotation(pos,ori);
		return productRotation;
	}
	/**
	 * Calcule la permutation inverse de this
	 * @return A^{-1}
	 */
	public Rotation permInv() {
		Integer[] pos = new Integer[7];
		Integer[] ori = new Integer[7];
		for(int i=0;i<Cube.TAILLE;i++) {
			pos[this.position.get(i)] = i+1;
			ori[this.position.get(i)] = (3-this.orientation.get(i))%3;
		}
		Rotation reverseRotation = new Rotation(pos,ori);
		return reverseRotation;
	}
	/**
	 * Renvoie la list de la position
	 * @return position
	 */
	public List<Integer> getPosition(){
		return this.position;
	}
	/**
	 * Renvoie la liste de l'orientation
	 * @return orientation
	 */
	public List<Integer> getOrientation(){
		return this.orientation;
	}
	
	
}