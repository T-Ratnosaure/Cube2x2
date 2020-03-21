import java.util.List;

/**
 *
 * Classe représentant un Cubie, c'est à dire un angle du cube
 *
 * @author Léandre Adam
 *
 */
public class Cubie {
	
	/**
	 * Les 3 couleurs du Cubie
	 */
	private List<String> colors;
	/**
	 * Position du Cubie dans le Cube résolu
	 */
	@SuppressWarnings("unused")
	private int positionResolue;
	/**
	 * Position du Cubie dans le Cube mélangé
	 */
	private int positionActuelle;
	/**
	 * Orientation du Cubie dans le Cube mélangé
	 */
	private int orientation;
	
	/**
	 * 
	 * @param posIn la position du Cubie dans le Cube résolu
	 * @param posAc la position du Cubie dans le Cube mélangé
	 * @param ori l'orientation du Cubie dans le Cube mélangé
	 */
	public Cubie(int posRes, int posAc, int ori) {
		positionResolue=posRes;
		positionActuelle=posAc;
		orientation=ori;
	}
	
	/**
	 * Récupère la position du Cubie dans le Cube mélangé
	 * @return positionActuelle : la position du Cubie dans le Cube mélangé
	 */
	public int getPos() {
		return positionActuelle;
	}
	
	/**
	 * Récupère l'orientation du cubie.
	 * @return orientation : l'orientation du cubie
	 */
	public int getOr() {
		return orientation;
	}
	
	/**
	 * Change la position du Cubie dans le Cube mélangé
	 * @param p : nouvelle position du Cubie
	 */
	public void setPos(int posAc) {
		positionActuelle=posAc;
	}
	
	/**
	 * Change l'orientation du Cubie dans le Cube mélangé
	 * @param p : nouvelle orientation du Cubie
	 */
	public void setOr(int oriAc) {
		orientation=oriAc;
	}
}