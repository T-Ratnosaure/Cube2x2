import java.util.Arrays;
import java.util.List;
/**
 * Classe présentant une rotation ( modèle mathématique de la permutation ) du Rubik's Cube
 * @author Léandre Adam
 * @author Aydin Abiar
 *
 */
public class Rotation {
	
	private String nom;
	
	/**
	 * liste des changements de position qu'effectue cette rotation ( le cubie à la position i a une nouvelle position position[i] )
	 */
	private List<Integer> position;
	
	/**
	 * liste des changements d'orientation qu'effectue cette rotation ( le cubie à la position i a une nouvelle orientation orientation[i] )
	 */
	private List<Integer> orientation;
	
	/**
	 * Constructeur de la Rotation
	 * @param pos : tableau des changements de position des 7 cubies
	 * @param ori : tableau des changements d'orientations des 7 cubies
	 */
	public Rotation(Integer[] pos,Integer[] ori, String nom) {
		this.position = Arrays.asList(pos);
		this.orientation= Arrays.asList(ori);		
		this.nom=nom;
	}
	
	
	/**
	 * calcule le produit B°this (d'abord this puis B)
	 * @param B une Rotation
	 * @return B°this
	 */
	public Rotation permProd(Rotation B) {
		Integer[] pos = new Integer[Cube.TAILLE];
		Integer[] ori = new Integer[Cube.TAILLE];
		for(int i=0;i<Cube.TAILLE;i++) {
			int posAfterThis = this.position.get(i);
			pos[i]=B.position.get(posAfterThis);
			ori[i]=(B.orientation.get(posAfterThis)+this.orientation.get(i))%3;
		}
		Rotation productRotation = new Rotation(pos,ori,this.toString()+B.toString());
		return productRotation;
	}
	
	/**
	 * Calcule la permutation inverse de this
	 * @return A^{-1}
	 */
	public Rotation permInv() {
		Integer[] pos = new Integer[Cube.TAILLE];
		Integer[] ori = new Integer[Cube.TAILLE];
		for(int i=0;i<Cube.TAILLE;i++) {
			pos[this.position.get(i)] = i;
			ori[this.position.get(i)] = (3-this.orientation.get(i))%3;
		}
		Rotation reverseRotation = new Rotation(pos,ori,this.toString()+"prime");
		return reverseRotation;
	}
	
	/**
	 * Renvoie la liste des changements de position de la rotation
	 * @return position
	 */
	public List<Integer> getPosition(){
		return this.position;
	}
	
	/**
	 * Renvoie la liste des changements d'orientation de la rotation
	 * @return orientation
	 */
	public List<Integer> getOrientation(){
		return this.orientation;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
	
}
