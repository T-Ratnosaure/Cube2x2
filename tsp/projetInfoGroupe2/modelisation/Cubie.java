package tsp.projetInfoGroupe2.modelisation;

/**
 *
 * Classe représentant un cubie.
 *
 * @author Léandre Adam
 * @author Aydin Abiar
 */

public class Cubie {


    /**
     * Position du cubie dans le cube résolu.
     */
    @SuppressWarnings("unused")
    private int positionResolue;

    /**
     * Position du cubie dans le cube mélangé.
     */
    private int positionActuelle;

    /**
     * Orientation du cubie dans le cube mélangé.
     */
    private int orientation;

    /**
     * Constructeur de la classe Cubie.
     * @param posRes la position du cubie dans le cube résolu
     * @param posAc la position du cubie dans le cube mélangé
     * @param ori l'orientation du cubie dans le cube mélangé
     */
    public Cubie(int posRes, int posAc, int ori) {
        positionResolue=posRes;
        positionActuelle=posAc;
        orientation=ori;
    }

    /**
     * Récupère la position du cubie dans le cube mélangé.
     * @return positionActuelle : la position du cubie dans le Cube mélangé
     */
    public int getPos() {
        return positionActuelle;
    }

    /**
     * Récupère l'orientation du cubie.
     * @return orientation : L'orientation du cubie
     */
    public int getOr() {
        return orientation;
    }

    /**
     * Récupère la position résolue du cubie dans le cube résolu étalon.
     * @return positionResolue : La position résolue du cubie dans le cube résolue
     */
    public int getPosRes() {
        return positionResolue;
    }


    /**
     * Change la position du cubie dans le cube mélangé.
     * @param posAc Nouvelle position du cubie
     */
    public void setPos(int posAc) {
        positionActuelle=posAc;
    }

    /**
     * Change l'orientation du Cubie dans le Cube mélangé.
     * @param oriAc Nouvelle orientation du cubie
     */
    public void setOr(int oriAc) {
        orientation=oriAc;
    }

}
