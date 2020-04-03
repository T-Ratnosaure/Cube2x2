package tsp.projetInfoGroupe2.modelisation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Classe représentant un cube.
 * @author Léandre Adam
 * @author Aydin Abiar
 */
public class Cube {

    /**
     * La taille du cube, c'est à dire le nombre de cubie hormis le cubie étalon.
     **/
    final public static int TAILLE=7;

    /**
     * Les 9 principales rotations.
     */
    final public static Rotation WW = new Rotation(new Integer[]{0,0,0,0,0,0,0}, new Integer[]{0,0,0,0,0,0,0},"WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
    @SuppressWarnings("unused")
    final public static Rotation R = new Rotation(new Integer[]{4,1,2,0,6,5,3}, new Integer[]{1,0,0,2,2,0,1},"R");
    @SuppressWarnings("unused")
    final public static Rotation F = new Rotation(new Integer[]{3,0,1,2,4,5,6},new Integer[]{2,1,2,1,0,0,0},"F");
    @SuppressWarnings("unused")
    final public static Rotation U = new Rotation(new Integer[]{1,5,2,3,0,4,6},new Integer[]{0,0,0,0,0,0,0},"U");
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
     * Tableau des 7 cubies du cube.
     */
    final public Cubie[] cubies = new Cubie[TAILLE];

    /**
     * Booléen qui détermine si le cube est résolu ou non.
     */
    private boolean solved;

    /**
     * Liste des rotations effectuées chronologiquement sur le cube.
     */
    public ArrayList<String> historicOfRotations;

    /**
     * Constructeur de la classe Cube.
     * @param posAc Liste des positions des 7 Cubies
     * @param ori Liste des orientations des 7 Cubies
     */
    public Cube(Integer[] posAc, Integer[] ori) {

        int[] POSITIONS_INITIALES = {0, 1, 2, 3, 4, 5, 6};
        for (int i = 0; i<TAILLE; i++ ) {
            cubies[i] = new Cubie(POSITIONS_INITIALES[i], posAc[i], ori[i]);

        }
        this.historicOfRotations = new ArrayList<String>();

        if (posAc.equals(Arrays.asList(0,1,2,3,4,5,6)) && ori.equals(Arrays.asList(0,0,0,0,0,0))) {
            this.solved = true;
        }
        else {
            this.solved=false;
        }
    }

    /**
     * Effectue une rotation sur le cube puis actualise son historique.
     * @param rotation
     */
    public void updateFromRotation(Rotation rotation) {
        for (int i = 0; i<TAILLE; i++) {
            int posBeforeRotation = this.cubies[i].getPos();
            int oriBeforeRotation = this.cubies[i].getOr();
            int oriAfterRotation = rotation.getOrientation().get(posBeforeRotation);
            int posAfterRotation = rotation.getPosition().get(posBeforeRotation);

            this.cubies[i].setOr((oriAfterRotation + oriBeforeRotation)%3);
            this.cubies[i].setPos(posAfterRotation);
        }

        historicOfRotations.add(rotation.toString());
    }

    /**
     * Effectue une liste de rotations sur this puis actualise son historique.
     * @param rotations ArrayList des rotations à faire
     */
    public void updateFromRotations(ArrayList<Rotation> rotations) {
        for(int i=0; i<rotations.size(); i++) {
            updateFromRotation(rotations.get(i));
        }
    }


    /**
     * Échange les deux positions des cubies 0 et 4. Utile pour les méthodes de résolutions
     */
    public void transposition() {
        ArrayList<Rotation> perm = new ArrayList<Rotation>(Arrays.asList(R,U2,Rprime,Uprime,R,U2,Rprime,F,Rprime,Fprime,R));
        updateFromRotations(perm);
    }

    /**
     * Place les cubies du bas.
     */
    public void placeDown(){
        ArrayList<Rotation> update = new ArrayList<Rotation>();

        switch (this.cubies[2].getPos()) {

            case 0:
                update.add(F2);
                break;

            case 1:
                update.addAll(Arrays.asList(F, U, Fprime));
                break;

            case 3:
                update.add(F);
                break;

            case 4:
                update.addAll(Arrays.asList(R2, F));
                break;

            case 5:
                update.addAll(Arrays.asList(F, Uprime, Fprime));
                break;

            case 6:
                update.addAll(Arrays.asList(R, F));
                break;

            default:
        }

        updateFromRotations(update);
        update.clear();

        switch (this.cubies[3].getPos()) {

            case 0:
                update.add(Rprime);
                break;

            case 1:
                update.addAll(Arrays.asList(Uprime, Rprime));
                break;

            case 4:
                update.add(R2);
                break;

            case 5:
                update.addAll(Arrays.asList(U2, Rprime));
                break;

            case 6:
                update.add(R);
                break;

            default:
        }

        updateFromRotations(update);
        update.clear();

        switch (this.cubies[6].getPos()) {

            case 0:
                update.addAll(Arrays.asList(F, Rprime, Fprime));
                break;

            case 1:
                update.addAll(Arrays.asList(Rprime, U2, R));
                break;

            case 4:
                update.addAll(Arrays.asList(Rprime, Uprime, R));
                break;

            case 5:
                update.addAll(Arrays.asList(Rprime, U, R));
                break;

            default:
        }

        updateFromRotations(update);
    }

    /**
     * Donne leur orientation aux cubies du bas.
     */
    public void orientationDown() {
        ArrayList<Rotation> update = new ArrayList<Rotation>();

        switch (this.cubies[2].getOr()) {

            case 1:
                update.addAll(Arrays.asList(F, U, F2, Uprime, F2, Uprime, Fprime, U, F, U, Fprime, Uprime, F2, Uprime, F2, U, F2));
                break;

            case 2:
                update.addAll(Arrays.asList(Fprime, U, F2, Uprime, F2, Uprime, Fprime, U, F, U, Fprime, Uprime, F2, Uprime, F2, U));
                break;

            default:
        }

        updateFromRotations(update);
        update.clear();

        switch (this.cubies[3].getOr()) {

            case 1:
                update.addAll(Arrays.asList(R, U, R2, Uprime, R2, Uprime, Rprime, U, R, U, Rprime, Uprime, R2, Uprime, R2, U, R2));
                break;

            case 2:
                update.addAll(Arrays.asList(Rprime, U, R2, Uprime, R2, Uprime, Rprime, U, R, U, Rprime, Uprime, R2, Uprime, R2, U));
                break;

            default:
        }

        updateFromRotations(update);
        update.clear();

        switch (this.cubies[6].getOr()) {

            case 1:
                update.addAll(Arrays.asList(R2, U, R2, Uprime, R2, Uprime, Rprime, U, R, U, Rprime, Uprime, R2, Uprime, R2, U, R));
                break;

            case 2:
                update.addAll(Arrays.asList(U, R2, Uprime, R2, Uprime, Rprime, U, R, U, Rprime, Uprime, R2, Uprime, R2, U, Rprime));
                break;

            default:
        }

        updateFromRotations(update);
    }

    /**
     * Pour rendre le code de la méthode OLL plus esthétique et moins redondant. Remplit listeBis des indices des cubies positionnés en 0, 1, 4 ou 5
     * @param listeBis ArrayList d'entier
     */
    public void getIndexOfPos0145(ArrayList<Integer> listeBis) {
        int[] x = {0,1,4,5};
        for ( int k : x ) {
            for (int j=0;j<TAILLE;j++) {
                if (this.cubies[j].getPos()==k) {
                    listeBis.add(j);
                }
            }
        }
    }

    /**
     * Oriente les cubies de la deuxième couronne.
     */
    public void OLL() {
        int[] x = {0,1,4,5};
        List<Integer> liste = Arrays.asList(this.cubies[0].getOr(),this.cubies[1].getOr(),this.cubies[4].getOr(),this.cubies[5].getOr());
        ArrayList<Integer> listeBis = new ArrayList<Integer>();
        ArrayList<Rotation> update = new ArrayList<Rotation>();

        switch (Collections.frequency(liste, 0)) {

            case 0:
                getIndexOfPos0145(listeBis);
                while (this.cubies[listeBis.get(0)].getOr()!=2 || this.cubies[listeBis.get(1)].getOr()!=1) {
                    updateFromRotation(U);
                    listeBis.clear();
                    getIndexOfPos0145(listeBis);
                }
                if (this.cubies[listeBis.get(2)].getOr()==1) {
                    update.addAll(Arrays.asList(R2, U2, Rprime, U2, R2));
                    updateFromRotations(update);
                    update.clear();
                }
                else {
                    update.addAll(Arrays.asList(U, F, R, U, Rprime, Uprime, R, U, Rprime, Uprime, Fprime));
                    updateFromRotations(update);
                    update.clear();
                }
                break;

            case 1:
                getIndexOfPos0145(listeBis);
                while (this.cubies[listeBis.get(0)].getOr()!=0) {
                    updateFromRotation(U);
                    listeBis.clear();
                    getIndexOfPos0145(listeBis);
                }
                update.addAll(Arrays.asList(R, U, Rprime, U, R, U2, Rprime));
                updateFromRotations(update);
                update.clear();
                break;

            case 2:
                getIndexOfPos0145(listeBis);
                if ((this.cubies[listeBis.get(0)].getOr()==this.cubies[listeBis.get(3)].getOr() && this.cubies[listeBis.get(0)].getOr()==0)||(this.cubies[listeBis.get(1)].getOr()==this.cubies[listeBis.get(2)].getOr() && this.cubies[listeBis.get(1)].getOr()==0)) {
                    while(this.cubies[listeBis.get(1)].getOr()!=1){
                        updateFromRotation(U);
                        update.addAll(Arrays.asList(Rprime, F, R, U, F, Uprime, Fprime));
                        listeBis.clear();
                        getIndexOfPos0145(listeBis);
                    }
                    updateFromRotations(update);
                    update.clear();
                }
                else {
                    while (this.cubies[listeBis.get(0)].getOr()!=this.cubies[listeBis.get(2)].getOr()) {
                        updateFromRotation(U);
                        listeBis.clear();
                        getIndexOfPos0145(listeBis);
                    }
                    if (this.cubies[listeBis.get(0)].getOr()!=this.cubies[listeBis.get(2)].getOr()) {
                        update.addAll(Arrays.asList(F, R, Fprime, Uprime, Rprime, Uprime, R));
                        updateFromRotations(update);
                        update.clear();
                    }
                    else {
                        update.addAll(Arrays.asList(F, R, U, Rprime, Uprime, Fprime));
                        updateFromRotations(update);
                        update.clear();
                    }
                }
                break;

            default:
        }
    }
    /**
     * Place correctement les cubies de la dernière couronne.
     */
    public void PLL() {
        ArrayList<Rotation> update = new ArrayList<Rotation>();

        while (this.cubies[0].getPos()!=0) {
            updateFromRotation(U);
        }

        if (this.cubies[1].getPos()==5) {
            updateFromRotation(U2);
            this.transposition();
            updateFromRotation(U2);
        }

        else if (this.cubies[1].getPos()==4) {
            update.addAll(Arrays.asList(Rprime, U, Rprime, F2, R, Fprime, U, Rprime, F2, R, Fprime, R, Uprime));
            updateFromRotations(update);
        }

        if (this.cubies[5].getPos()==4) {
            updateFromRotation(U);
            this.transposition();
            updateFromRotation(U);
        }
    }

    /**
     * Optimise la liste des rotations effectuées.
     * @return La liste des coups optimisée renvoi
     */
    public ArrayList<Rotation> reduction() {
        ArrayList<Integer> renvoi = new ArrayList<Integer>();
        ArrayList<Rotation> renvoi2 = new ArrayList<Rotation>();
        int n = this.historicOfRotations.size();
        int i=0;
        int j;
        int s;
        int w;
        for(int k=0;k<n;k++) {
            if(historicOfRotations.get(k)=="R") {
                renvoi.add(0);
                renvoi.add(1);
            }
            else if(historicOfRotations.get(k)=="Rprime") {
                renvoi.add(0);
                renvoi.add(3);
            }
            else if(historicOfRotations.get(k)=="R2") {
                renvoi.add(0);
                renvoi.add(2);
            }
            else if(historicOfRotations.get(k)=="F") {
                renvoi.add(1);
                renvoi.add(1);
            }
            else if(historicOfRotations.get(k)=="Fprime") {
                renvoi.add(1);
                renvoi.add(3);
            }
            else if(historicOfRotations.get(k)=="F2") {
                renvoi.add(1);
                renvoi.add(2);
            }
            else if(historicOfRotations.get(k)=="U") {
                renvoi.add(2);
                renvoi.add(1);
            }
            else if(historicOfRotations.get(k)=="Uprime") {
                renvoi.add(2);
                renvoi.add(3);
            }
            else if(historicOfRotations.get(k)=="U2") {
                renvoi.add(2);
                renvoi.add(2);
            }
        }
        while(i<renvoi.size()/2-1) {
            j=i;
            s=0;
            w=renvoi.get(2*i);
            while(j<renvoi.size()-1 && renvoi.get(2*j)==w) {
                j++;
            }
            for(int l=i;l<j;l++) {
                s=s+renvoi.get(2*l+1);
            }
            s=s%4;
            if(s!=0) {
                renvoi.add(2*j,s);
                renvoi.add(2*j,w);
            }
            for(int l=2*i;l<2*j;l++) {
                renvoi.remove(l);
            }
            if(j!=i+1 && i>0) {
                i=i-1;
            }
            else {
                i=i+1;
            }
        }
        for(int k=0;k<n/2;k++) {
            if(renvoi.get(2*k)==0 && renvoi.get(2*k+1)==1) {
                renvoi2.add(R);
            }
            else if(renvoi.get(2*k)==0 && renvoi.get(2*k+1)==3) {
                renvoi2.add(Rprime);
            }
            else if(renvoi.get(2*k)==0 && renvoi.get(2*k+1)==2) {
                renvoi2.add(R2);
            }
            else if(renvoi.get(2*k)==1 && renvoi.get(2*k+1)==1) {
                renvoi2.add(F);
            }
            else if(renvoi.get(2*k)==1 && renvoi.get(2*k+1)==3) {
                renvoi2.add(Fprime);
            }
            else if(renvoi.get(2*k)==1 && renvoi.get(2*k+1)==2) {
                renvoi2.add(F2);
            }
            else if(renvoi.get(2*k)==2 && renvoi.get(2*k+1)==1) {
                renvoi2.add(U);
            }
            else if(renvoi.get(2*k)==2 && renvoi.get(2*k+1)==3) {
                renvoi2.add(Uprime);
            }
            else if(renvoi.get(2*k)==2 && renvoi.get(2*k+1)==2) {
                renvoi2.add(U2);
            }
        }
        return renvoi2;
    }

    /**
     * Actualise l'état du cube. Résolu ou pas ?
     */
    public void checkIfSolved() {
        ArrayList<Integer> posOfCubiesAct = new ArrayList<Integer>();
        ArrayList<Integer> oriOfCubiesAct = new ArrayList<Integer>();

        for (int i = 0; i < TAILLE; i++) {
            posOfCubiesAct.add(this.cubies[i].getPos());
            oriOfCubiesAct.add(this.cubies[i].getOr());
        }

        if (posOfCubiesAct.equals(Arrays.asList(0,1,2,3,4,5,6)) && oriOfCubiesAct.equals(Arrays.asList(0,0,0,0,0,0,0))) {
            this.solved = true;
        }
        else {
            this.solved = false;
        }
    }

    /**
     * Applique la méthode de résolution humaine au cube.
     */
    public void humanSolver(){
        this.placeDown();
        this.orientationDown();
        this.OLL();
        this.PLL();
        //this.reduction();
        checkIfSolved();

        while (this.solved==false) {
            humanSolver();
            checkIfSolved();
        }

    }

    /**
     * Transforme le cube en un cube aléatoire. Applique pour cela 20 rotations aléatoires à un cube étalon puis associe le résultat à l'instance.
     */
    public void randomCube(){

        Cube randomCube = new Cube(new Integer[] {0,1,2,3,4,5,6}, new Integer[] {0,0,0,0,0,0,0});

        List<Rotation> listOfRotations = Arrays.asList(Cube.R, Cube.F, Cube.U, Cube.Rprime, Cube.Fprime, Cube.Uprime, Cube.R2, Cube.F2, Cube.U2);

        /* Pour générer des nombres aléatoires */ Random random = new Random();
        final int NUMBER_OF_RANDOM_DONE_ROTATIONS = 20;
        int numberOfRandomDoneRotations = 0;

        // On effectue 20 rotations au hasard
        while (numberOfRandomDoneRotations < NUMBER_OF_RANDOM_DONE_ROTATIONS) {

            int chosenRotation = random.nextInt(listOfRotations.size());
            randomCube.updateFromRotation(listOfRotations.get(chosenRotation));
            numberOfRandomDoneRotations++;

        }

        // On attribue les positions et orientations des cubies du cube aléatoire à l'instance this
        for (int i = 0; i<TAILLE; i++) {
            this.cubies[i].setPos(randomCube.cubies[i].getPos());
            this.cubies[i].setOr(randomCube.cubies[i].getOr());
        }

        // On attribue l'historique du cube aléatoire à l'instance this
        for (int i = 0; i<NUMBER_OF_RANDOM_DONE_ROTATIONS; i++) {
            this.historicOfRotations.add(randomCube.historicOfRotations.get(i));
        }
    }

    /**
     * Renvoie une ligne décrivant chacune un cubie. C'est à dire son numéro, sa position actuelle et son orientation actuelle
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
