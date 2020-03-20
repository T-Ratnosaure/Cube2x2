/**
 *
 * Classe repr�sentant un Cubie, c'est � dire un angle du cube
 *
 * @author L�andre Adam
 *
 */
public class Cubie {
	/**
	 * repr�sente la position du Cubie dans le Cube r�solu
	 */
	@SuppressWarnings("unused")
	private int positionInitiale;
	/**
	 * repr�sente la position du Cubie dans le Cube m�lang�
	 */
	private int positionActuelle;
	/**
	 * repr�sente l'orientation du Cubie dans le Cube m�lang�
	 */
	private int orientation;
	/**
	 * 
	 * @param posIn la position du Cubie dans le Cube r�solu
	 * @param posAc la position du Cubie dans le Cube m�lang�
	 * @param ori l'orientation du Cubie dans le Cube m�lang�
	 */
	public Cubie(int posIn, int posAc, int ori) {
		positionInitiale=posIn;
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
	 * Recupere l'orientation du cubie.
	 * @return orientation : l'orientation du cubie
	 */
	public int getOr() {
		return orientation;
	}
	/**
	 * Change la position du Cubie dans le Cube m�lang�
	 * @param p : nouvelle position du Cubie
	 */
	public void setPos(int p) {
		positionActuelle=p;
	}
	/**
	 * Change l'orientation du Cubie dans le Cube m�lang�
	 * @param p : nouvelle orientation du Cubie
	 */
	public void setOr(int p) {
		orientation=p;
	}
}