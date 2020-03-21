import java.util.List;

/**
 *
 * Classe repr�sentant un Cubie, c'est � dire un angle du cube
 *
 * @author L�andre Adam
 *
 */
public class Cubie {
	
	/**
	 * Les 3 couleurs du Cubie
	 */
	private List<String> colors;
	/**
	 * Position du Cubie dans le Cube r�solu
	 */
	@SuppressWarnings("unused")
	private int positionResolue;
	/**
	 * Position du Cubie dans le Cube m�lang�
	 */
	private int positionActuelle;
	/**
	 * Orientation du Cubie dans le Cube m�lang�
	 */
	private int orientation;
	
	/**
	 * 
	 * @param posIn la position du Cubie dans le Cube r�solu
	 * @param posAc la position du Cubie dans le Cube m�lang�
	 * @param ori l'orientation du Cubie dans le Cube m�lang�
	 */
	public Cubie(int posRes, int posAc, int ori) {
		positionResolue=posRes;
		positionActuelle=posAc;
		orientation=ori;
	}
	
	/**
	 * R�cup�re la position du Cubie dans le Cube m�lang�
	 * @return positionActuelle : la position du Cubie dans le Cube m�lang�
	 */
	public int getPos() {
		return positionActuelle;
	}
	
	/**
	 * R�cup�re l'orientation du cubie.
	 * @return orientation : l'orientation du cubie
	 */
	public int getOr() {
		return orientation;
	}
	
	/**
	 * Change la position du Cubie dans le Cube m�lang�
	 * @param p : nouvelle position du Cubie
	 */
	public void setPos(int posAc) {
		positionActuelle=posAc;
	}
	
	/**
	 * Change l'orientation du Cubie dans le Cube m�lang�
	 * @param p : nouvelle orientation du Cubie
	 */
	public void setOr(int oriAc) {
		orientation=oriAc;
	}
}