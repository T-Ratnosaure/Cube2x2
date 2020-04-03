package tsp.projetInfoGroupe2.modelisation;

import java.util.Arrays;
import java.util.List;

/**
 * Classe représentant une rotation.
 * @author Léandre Adam
 * @author Aydin Abiar
 */

public class Rotation {

    private String nom;

    /**
     * Liste des changements de position qu'effectue cette rotation. Le cubie à la position i a une nouvelle position position[i]
     */
    private List<Integer> position;

    /**
     * Liste des changements d'orientation qu'effectue cette rotation. Le cubie à la position i a une nouvelle orientation orientation[i]
     */
    private List<Integer> orientation;

    /**
     * Constructeur de la classe Rotation.
     * @param pos Tableau des changements de position des 7 cubies
     * @param ori Tableau des changements d'orientations des 7 cubies
     * @param nom Nom de la rotation
     */
    public Rotation(Integer[] pos,Integer[] ori, String nom) {
        this.position = Arrays.asList(pos);
        this.orientation= Arrays.asList(ori);
        this.nom=nom;
    }


    /**
     * Calcule le produit d'une rotation avec l'instance this puis le renvoie. D'abord this puis B.
     * @param B une rotation
     * @return productRotation : Le produit B°this
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
     * Calcule la permutation inverse de l'instance this.
     * @return reverseRotation : L'inverse A^{-1}
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
     * Renvoie la liste des changements de position de la rotation.
     * @return position
     */
    public List<Integer> getPosition(){
        return this.position;
    }

    /**
     * Renvoie la liste des changements d'orientation de la rotation.
     * @return orientation
     */
    public List<Integer> getOrientation(){
        return this.orientation;
    }

    /**
     * Renvoie le nom de la rotation.
     * @return nom
     */
    @Override
    public String toString() {
        return nom;
    }

}