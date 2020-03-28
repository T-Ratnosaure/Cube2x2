package com.aydinabiar.rubikscubeprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // On définit les constantes du Cube

    /**
     * 2 constantes : Un tableau des positions de chaque Cubie dans le Cube résolu; la taille du Cube = nombre de Cubies)
     **/
    final public int TAILLE=7;

    /**
     * 9 constantes Rotations, toutes les rotations possibles lors du jeu
     */
    final public Rotation WW = new Rotation(new Integer[]{0,0,0,0,0,0,0}, new Integer[]{0,0,0,0,0,0,0},"WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
    @SuppressWarnings("unused")
    final public Rotation R = new Rotation(new Integer[]{4,1,2,0,6,5,3}, new Integer[]{1,0,0,2,2,0,1},"R");
    @SuppressWarnings("unused")
    final public Rotation F = new Rotation(new Integer[]{3,0,1,2,4,5,6},new Integer[]{2,1,2,1,0,0,0},"F");
    @SuppressWarnings("unused")
    final public Rotation U = new Rotation(new Integer[]{1,5,2,3,0,4,6},new Integer[]{0,0,0,0,0,0,0},"U");
    @SuppressWarnings("unused")
    final public Rotation Fprime = F.permInv();
    @SuppressWarnings("unused")
    final public Rotation Uprime = U.permInv();
    @SuppressWarnings("unused")
    final public Rotation Rprime = R.permInv();
    @SuppressWarnings("unused")
    final public Rotation R2 = R.permProd(R);
    @SuppressWarnings("unused")
    final public Rotation U2 = U.permProd(U);
    @SuppressWarnings("unused")
    final public Rotation F2 = F.permProd(F);

    // On importe les classes Cubie, Rotation et Cube

    /**
     *
     * Classe représentant un Cubie, c'est à dire un angle du cube
     *
     * @author Léandre Adam
     * @author Aydin Abiar
     */

    public class Cubie {


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
         * @param posRes la position du Cubie dans le Cube résolu
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
         * Récupère la position résolue du Cubie dans le Cube résolu étalon
         * @return positionResolue : la position résolue du Cubie dans le Cube résolue
         */
        public int getPosRes() {
            return positionResolue;
        }


        /**
         * Change la position du Cubie dans le Cube mélangé
         * @param posAc : nouvelle position du Cubie
         */
        public void setPos(int posAc) {
            positionActuelle=posAc;
        }

        /**
         * Change l'orientation du Cubie dans le Cube mélangé
         * @param oriAc : nouvelle orientation du Cubie
         */
        public void setOr(int oriAc) {
            orientation=oriAc;
        }

    }


    /**
     * Classe présentant une rotation ( modèle mathématique de la permutation ) du Rubik's Cube
     * @author Léandre Adam
     * @author Aydin Abiar
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
            Integer[] pos = new Integer[TAILLE];
            Integer[] ori = new Integer[TAILLE];
            for(int i=0;i<TAILLE;i++) {
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
            Integer[] pos = new Integer[TAILLE];
            Integer[] ori = new Integer[TAILLE];
            for(int i=0;i<TAILLE;i++) {
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

    /**
     * Classe représentant le Cube de Rubik
     * @author Léandre Adam
     * @author Aydin Abiar
     */
    public class Cube {

        /**
         * Tableau des 7 Cubies du Cube
         */
        final public Cubie[] cubies = new Cubie[TAILLE];

        /**
         * Liste des 7 triplets de couleurs de chaque Cubie du Cube (dans l'ordre des positions résolues étalons). Le Cube étalon est jaune au-dessus, vert devant, orange à droite.
         */
        final public List<List<String>> colorsOfCubies = Arrays.asList(Arrays.asList("yellow","#FF9800","green"), Arrays.asList("yellow","green","red"), Arrays.asList("white","red","green"), Arrays.asList("white","green","#FF9800"), Arrays.asList("yellow","blue","#FF9800"), Arrays.asList("yellow","red","blue"), Arrays.asList("white","#FF9800","blue"));

        /**
         * Booléen qui détermine si le cube est résolu ou non
         */
        @SuppressWarnings("unused")
        private boolean solved;

        /**
         * Liste des Rotations effectuées chronologiquement sur le cube
         */
        public ArrayList<Rotation> doneRotations;

        /**
         * Constructeur du Cube
         * @param posAc : List des positions des 7 Cubies
         * @param ori : List des orientations des 7 Cubies
         * @param doneRotations : ArrayList des rotations déjà effectuées
         */
        public Cube(Integer[] posAc, Integer[] ori, ArrayList<Rotation> doneRotations) {

            int[] POSITIONS_INITIALES = {0, 1, 2, 3, 4, 5, 6};
            for (int i = 0; i<TAILLE; i++ ) {
                cubies[i] = new Cubie(POSITIONS_INITIALES[i], posAc[i], ori[i]);

            }
            this.doneRotations = doneRotations;

            if (posAc.equals(Arrays.asList(0,1,2,3,4,5,6)) && ori.equals(Arrays.asList(0,0,0,0,0,0))) {
                this.solved = true;
            }
            else {
                this.solved=false;
            }
        }

        /**
         * Effectue une Rotation sur this puis incrémente la liste des Rotations effectuées
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

            this.doneRotations.add(rotation);
            historicOfRotations.add(rotation.toString());
        }

        /**
         * Effectue une liste de Rotations sur this puis incrémente la liste des Rotations effectuées
         * @param rotations un ArrayList des rotations à faire
         */
        public void updateFromRotations(ArrayList<Rotation> rotations) {
            for(int i=0; i<rotations.size(); i++) {
                updateFromRotation(rotations.get(i));
            }
        }


        /**
         * Échange les deux positions des Cubies 0 et 4
         */
        public void transposition() {
            ArrayList<Rotation> perm = new ArrayList<Rotation>(Arrays.asList(R,U2,Rprime,Uprime,R,U2,Rprime,F,Rprime,Fprime,R));
            updateFromRotations(perm);
        }

        /**
         * Place à leurs places les cubies du bas
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
         * Donne leur orientation aux cubies du bas
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
         * Pour rendre le code plus esthétique et moins redondant. Remplit listeBis des indices des cubies positionnés en 0, 1, 4 ou 5
         * @param listeBis un ArrayList d'entier
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
         * Oriente les cubies de la deuxième couronne
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
         * Place correctement les cubies de la dernière couronne
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
         * Optimise la liste
         * @return la liste des coups optimisée renvoi2
         */
        public ArrayList<Rotation> reduction() {
            ArrayList<Integer> renvoi = new ArrayList<Integer>();
            ArrayList<Rotation> renvoi2 = new ArrayList<Rotation>();
            int n = this.doneRotations.size();
            int i=0;
            int j;
            int s;
            int w;
            for(int k=0;k<n;k++) {
                if(doneRotations.get(k)==R) {
                    renvoi.add(0);
                    renvoi.add(1);
                }
                else if(doneRotations.get(k)==Rprime) {
                    renvoi.add(0);
                    renvoi.add(3);
                }
                else if(doneRotations.get(k)==R2) {
                    renvoi.add(0);
                    renvoi.add(2);
                }
                else if(doneRotations.get(k)==F) {
                    renvoi.add(1);
                    renvoi.add(1);
                }
                else if(doneRotations.get(k)==Fprime) {
                    renvoi.add(1);
                    renvoi.add(3);
                }
                else if(doneRotations.get(k)==F2) {
                    renvoi.add(1);
                    renvoi.add(2);
                }
                else if(doneRotations.get(k)==U) {
                    renvoi.add(2);
                    renvoi.add(1);
                }
                else if(doneRotations.get(k)==Uprime) {
                    renvoi.add(2);
                    renvoi.add(3);
                }
                else if(doneRotations.get(k)==U2) {
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
         * Actualise l'état du cube : Résolu ou pas
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

    // ______________________________________________________ PARTIE GRAPHIQUE


    // On définit les 26 cases du cube
    // face Back
    TextView caseB0;
    TextView caseB1;
    TextView caseB2;
    TextView caseB3;

    // face Up
    TextView caseU0;
    TextView caseU1;
    TextView caseU2;
    TextView caseU3;

    // face Front
    TextView caseF0;
    TextView caseF1;
    TextView caseF2;
    TextView caseF3;

    // face Down
    TextView caseD0;
    TextView caseD1;
    TextView caseD2;
    TextView caseD3;

    // face Right
    TextView caseR0;
    TextView caseR1;
    TextView caseR2;
    TextView caseR3;

    // face Left
    TextView caseL0;
    TextView caseL1;
    TextView caseL2;
    TextView caseL3;

    // Liste des cases
    List<TextView> cases;

    // Lignes, Colonnes et Centres du patron
    List<TextView> line0;
    List<TextView> line1;
    List<TextView> column0;
    List<TextView> column1;
    List<TextView> center0;
    List<TextView> center1;

    // Faces du patron;
    List<TextView> faceB;
    List<TextView> faceU;
    List<TextView> faceF;
    List<TextView> faceD;
    List<TextView> faceR;
    List<TextView> faceL;

    //Liste des cases de chaque cubie. On commence par la case UP ou DOWN du cube résolu étalon puis on suit le sens des aiguilles d'une montre
    List<List<TextView>> textViewOfCubies;
    /**
     * Liste des 7 triplets de couleurs de chaque Cubie du Cube. On commence par la couleur jaune (UP) ou blanche (DOWN) puis on suit le sens des aiguilles d'une montre
     **/
    public List<List<String>> colorsOfCubies;

    // Cube de travail
    public static Cube cube;

    // Liste des rotations effectuées
    public static ArrayList<String> historicOfRotations;

    // Méthode pour mélanger le cube
    public void randomCube(){

        cube = new Cube(new Integer[] {0,1,2,3,4,5,6}, new Integer[] {0,0,0,0,0,0,0}, new ArrayList<Rotation>());

        List<Rotation> listOfRotations = Arrays.asList(R, F, U, Rprime, Fprime, Uprime, R2, F2, U2);
        Random random = new Random(); // Pour générer des nombres aléatoires
        int numberOfRandomhistoricOfRotations = 0;

        // On effectue 50 rotations au hasard
        while (numberOfRandomhistoricOfRotations < 50) {

            int chosenRotation = random.nextInt(9);
            cube.updateFromRotation(listOfRotations.get(chosenRotation));
            numberOfRandomhistoricOfRotations++;

        }

        // On efface les rotations du mélange de la liste des rotations effectuées et de l'historique
        cube.doneRotations.clear();
        historicOfRotations.clear();
        historicOfRotations.add("Back to main page");

        // On initialise le Cubie 8 étalon
        showCubieEtalon();

    }

    // Rotation d'une Ligne, Colonne ou Centre (=LLC)
    public void rotationOfLCC(List<TextView> llc, View view) {

        // On stocke les couleurs des cases concernées
        int[] colorListInt = new int[8];
        for (int i =0; i<llc.size(); i++) {
            colorListInt[i]=((ColorDrawable) llc.get(i).getBackground()).getColor();
        }


        // On fait la rotation en attribuant aux cases leur nouvelle couleur

        // 2 cas : rotation positive ou négative

        if (((Button) view).getText().equals("+")) {

            ((ColorDrawable) llc.get(0).getBackground()).setColor(colorListInt[6]);
            ((ColorDrawable) llc.get(1).getBackground()).setColor(colorListInt[7]);
            for (int i = 2; i<llc.size(); i++) {
                ((ColorDrawable) llc.get(i).getBackground()).setColor(colorListInt[i-2]);
            }

        } else if (((Button) view).getText().equals("-")) {

            ((ColorDrawable) llc.get(6).getBackground()).setColor(colorListInt[0]);
            ((ColorDrawable) llc.get(7).getBackground()).setColor(colorListInt[1]);
            for (int i = 0; i<llc.size()-2; i++) {
                ((ColorDrawable) llc.get(i).getBackground()).setColor(colorListInt[i+2]);
            }

        }

    }

    // Rotation d'une face
    public void rotationOfFace(List<TextView> face, View view) {

        // On stocke les couleurs des cases concernées
        int[] colorListInt = new int[4];
        for (int i =0; i<face.size(); i++) {
            colorListInt[i]=((ColorDrawable) face.get(i).getBackground()).getColor();
        }

        // On fait la rotation en attribuant aux cases leur nouvelle couleur

        // 2 cas : rotation positive ou négative

        if (((Button) view).getText().equals("+")) {

            ((ColorDrawable) face.get(0).getBackground()).setColor(colorListInt[3]);
            for (int i = 1; i<face.size(); i++) {
                ((ColorDrawable) face.get(i).getBackground()).setColor(colorListInt[i-1]);
            }

        } else if (((Button) view).getText().equals("-")) {

            ((ColorDrawable) face.get(3).getBackground()).setColor(colorListInt[0]);
            for (int i = 0; i<face.size()-1; i++) {
                ((ColorDrawable) face.get(i).getBackground()).setColor(colorListInt[i+1]);
            }

        }

    }

    // Méthodes des boutons de rotations

    public void rotationL0plus(View view) {

        rotationOfLCC(line0, view);
        rotationOfFace(faceB, view);

        // Incrémentation de la liste des rotations effectuées
        historicOfRotations.add("Fprime");

        // Rotation sur le Cube
        cube.updateFromRotation(Fprime);

    }

    public void rotationL0minus(View view) {

        rotationOfLCC(line0, view);
        rotationOfFace(faceB, view);

        historicOfRotations.add("F");

        cube.updateFromRotation(F);

    }

    public void rotationL1plus(View view) {

        rotationOfLCC(line1, view);
        rotationOfFace(faceF, view);

        historicOfRotations.add("F");

        cube.updateFromRotation(F);

    }

    public void rotationL1minus(View view) {

        rotationOfLCC(line1, view);
        rotationOfFace(faceF, view);

        historicOfRotations.add("Fprime");

        cube.updateFromRotation(Fprime);

    }

    public void rotationC0plus(View view) {

        rotationOfLCC(column0, view);
        rotationOfFace(faceL, view);

        historicOfRotations.add("R");

        cube.updateFromRotation(R);

    }

    public void rotationC0minus(View view) {

        rotationOfLCC(column0, view);
        rotationOfFace(faceL, view);

        historicOfRotations.add("Rprime");

        cube.updateFromRotation(Rprime);

    }

    public void rotationC1plus(View view) {

        rotationOfLCC(column1, view);
        rotationOfFace(faceR, view);

        historicOfRotations.add("Rprime");

        cube.updateFromRotation(Rprime);

    }

    public void rotationC1minus(View view) {

        rotationOfLCC(column1, view);
        rotationOfFace(faceR, view);

        historicOfRotations.add("R");

        cube.updateFromRotation(R);

    }

    public void rotationCenter0plus(View view) {

        rotationOfLCC(center0, view);
        rotationOfFace(faceU, view);

        historicOfRotations.add("Uprime");

        cube.updateFromRotation(Uprime);

    }

    public void rotationCenter0minus(View view) {

        rotationOfLCC(center0, view);
        rotationOfFace(faceU, view);

        historicOfRotations.add("U");

        cube.updateFromRotation(U);

    }

    public void rotationCenter1plus(View view) {

        rotationOfLCC(center1, view);
        rotationOfFace(faceD, view);

        historicOfRotations.add("U");

        cube.updateFromRotation(U);

    }

    public void rotationCenter1minus(View view) {

        rotationOfLCC(center1, view);
        rotationOfFace(faceD, view);

        historicOfRotations.add("Uprime");

        cube.updateFromRotation(Uprime);

    }

    public void humanMethodClick(View view) {

        cube.humanSolver();
        showCube();

    }

    // Button Read the Solution !
    public void showRotations(View view) {

        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(intent);

    }

    //Button New Cube !

    public void newCubeClick(View view) {

        randomCube();
        showCube();

    }

    public void showCubieEtalon() {

        caseD1.setBackgroundColor(Color.parseColor("white"));
        caseB0.setBackgroundColor(Color.parseColor("blue"));
        caseL0.setBackgroundColor(Color.parseColor("red"));

    }

    public void showCubie(Cubie cubie) {

        // On détermine les couleurs liées au Cubie
        int posRes = cubie.getPosRes();
        List<String> colorsOfCubie = colorsOfCubies.get(posRes);

        // On détermine les 3 cases (TextView) qui seront modifiées par le Cubie à la position pos
        int pos = cubie.getPos();
        List<TextView> textViewsOfCubie = textViewOfCubies.get(pos);

        // On détermine l'emplacement des couleurs du Cubie sur les 3 cases (Textview) puis on colorie les cases
        int ori = cubie.getOr();
        for (int i = 0; i < textViewsOfCubie.size(); i++) {

            textViewsOfCubie.get(i).setBackgroundColor(Color.parseColor(colorsOfCubie.get((i+3-ori)%3)));

        }

    }

    public void showCube() {

        // On montre chaque Cubie
        for (int i = 0; i < TAILLE; i++) {

            showCubie(cube.cubies[i]);

        }

        // On oublie pas le Cubie étalon
        showCubieEtalon();

    }

    public static boolean firstTimeOrNot = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.aydinabiar.rubikscubeprototype.R.layout.activity_main);

        // Attribution des cases
        caseB0 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseB0);
        caseB1 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseB1);
        caseB2 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseB2);
        caseB3 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseB3);

        caseU0 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseU0);
        caseU1 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseU1);
        caseU2 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseU2);
        caseU3 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseU3);

        caseF0 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseF0);
        caseF1 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseF1);
        caseF2 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseF2);
        caseF3 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseF3);

        caseD0 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseD0);
        caseD1 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseD1);
        caseD2 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseD2);
        caseD3 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseD3);

        caseR0 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseR0);
        caseR1 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseR1);
        caseR2 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseR2);
        caseR3 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseR3);

        caseL0 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseL0);
        caseL1 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseL1);
        caseL2 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseL2);
        caseL3 = (TextView) findViewById(com.aydinabiar.rubikscubeprototype.R.id.caseL3);

        // Attribution de la liste des cases
        cases = Arrays.asList(caseB0,caseB1,caseB2,caseB3,
                caseU0,caseU1,caseU2,caseU3,
                caseF0,caseF1,caseF2,caseF3,
                caseD0,caseD1,caseD2,caseD3,
                caseR0,caseR1,caseR2,caseR3,
                caseL0,caseL1,caseL2,caseL3);

        // Attribution des Lignes, Colonnes et Centres
        line0 = Arrays.asList(caseL0,caseL3,caseU0,caseU3,caseR0,caseR3,caseD2,caseD1);
        line1 = Arrays.asList(caseL1,caseL2,caseU1,caseU2,caseR1,caseR2,caseD3,caseD0);
        column0 = Arrays.asList(caseB0,caseB1,caseU0,caseU1,caseF0,caseF1,caseD0,caseD1);
        column1 = Arrays.asList(caseB3,caseB2,caseU3,caseU2,caseF3,caseF2,caseD3,caseD2);
        center0 = Arrays.asList(caseL2,caseF0,caseF3,caseR1,caseR0,caseB2,caseB1,caseL3);
        center1 = Arrays.asList(caseL1,caseF1,caseF2,caseR2,caseR3,caseB3,caseB0,caseL0);

        // Attribution des faces
        faceB = Arrays.asList(caseB0,caseB1,caseB2,caseB3);
        faceU = Arrays.asList(caseU0,caseU1,caseU2,caseU3);
        faceF = Arrays.asList(caseF0,caseF3,caseF2,caseF1);
        faceD = Arrays.asList(caseD0,caseD3,caseD2,caseD1);
        faceR = Arrays.asList(caseR0,caseR1,caseR2,caseR3);
        faceL = Arrays.asList(caseL0,caseL3,caseL2,caseL1);

        // Attribution de la liste des TextView des Cubie
        textViewOfCubies = Arrays.asList(Arrays.asList(caseU2,caseR1,caseF3), Arrays.asList(caseU1,caseF0,caseL2), Arrays.asList(caseD0,caseL1,caseF1), Arrays.asList(caseD3,caseF2,caseR2), Arrays.asList(caseU3,caseB2,caseR0), Arrays.asList(caseU0,caseL3,caseB1), Arrays.asList(caseD2,caseR3,caseB3));

        // Attribution de la liste des couleurs des Cubie
        colorsOfCubies = Arrays.asList(Arrays.asList("yellow","#FF9800","green"), Arrays.asList("yellow","green","red"), Arrays.asList("white","red","green"), Arrays.asList("white","green","#FF9800"), Arrays.asList("yellow","blue","#FF9800"), Arrays.asList("yellow","red","blue"), Arrays.asList("white","#FF9800","blue"));

        // Attribution de la liste des rotations effectuées (ListView)
        historicOfRotations = new ArrayList<String>();
        historicOfRotations.add("Back to main page");

        if (firstTimeOrNot) {
            randomCube();
            firstTimeOrNot = false;
        }

        showCube();

    }
}
