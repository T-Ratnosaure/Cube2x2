package tsp.projetInfoGroupe2.rubikscubeprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import tsp.projetInfoGroupe2.modelisation.*;

/**
 * Classe représentant la première page de l'application. Celle du cube affiché à l'utilisateur
 * @author Aydin Abiar
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Liste des 4 cases de la face Back du cube.
     */
    TextView caseB0;
    TextView caseB1;
    TextView caseB2;
    TextView caseB3;

    /**
     * Liste des 4 cases de la face Up du cube.
     */
    TextView caseU0;
    TextView caseU1;
    TextView caseU2;
    TextView caseU3;

    /**
     * Liste des 4 cases de la face Front du cube.
     */
    TextView caseF0;
    TextView caseF1;
    TextView caseF2;
    TextView caseF3;

    /**
     * Liste des 4 cases de la face Down du cube.
     */
    TextView caseD0;
    TextView caseD1;
    TextView caseD2;
    TextView caseD3;

    /**
     * Liste des 4 cases de la face Right du cube.
     */
    TextView caseR0;
    TextView caseR1;
    TextView caseR2;
    TextView caseR3;

    /**
     * Liste des 4 cases de la face Left du cube.
     */
    TextView caseL0;
    TextView caseL1;
    TextView caseL2;
    TextView caseL3;

    /**
     * Liste des 24 cases du cube.
     */
    List<TextView> cases;

    /**
     * Listes des cases des lignes, colonnes et centres (= LCC).
     */
    List<TextView> line0;
    List<TextView> line1;
    List<TextView> column0;
    List<TextView> column1;
    List<TextView> center0;
    List<TextView> center1;

    /**
     * Listes des cases des 6 faces du Cube.
     */
    List<TextView> faceB;
    List<TextView> faceU;
    List<TextView> faceF;
    List<TextView> faceD;
    List<TextView> faceR;
    List<TextView> faceL;

    /**
     * Liste ordonnée des trios de cases associées aux cubies du cube étalon. Par exemple casesOfCubies[i] donne les 3 cases du cubie numéro i du cube étalon.
     */
    List<List<TextView>> casesOfCubies;

    /**
     * Liste ordonnée des trios de couleurs associées aux cubies du cube étalon. Par exemple colorsOfCubies[i] donne les 3 couleurs du cubie numéro i du cube étalon.
     **/
    public List<List<String>> colorsOfCubies;

    /**
     * Cube de travail.
     */
    public static Cube cube;

    /**
     * Met à jour une ligne, colonne ou centre (=LCC) de la vue après une rotation quelconque.
     * @param lcc la ligne, colonne ou centre concerné
     * @param direction le sens de la rotation (+ ou -)
     */
    public void updateLCCViewFromRotation(List<TextView> lcc, String direction) {

        // On stocke les couleurs des cases concernées
        int[] colorListInt = new int[8];
        for (int i =0; i<lcc.size(); i++) {
            colorListInt[i]=((ColorDrawable) lcc.get(i).getBackground()).getColor();
        }


        // On fait la rotation en attribuant aux cases leur nouvelle couleur

        // 2 cas : rotation positive ou négative

        if (direction.equals("+")) {
            // On s'occupe des 2 cases aux limites (non atteint par la boucle)
            ((ColorDrawable) lcc.get(0).getBackground()).setColor(colorListInt[6]);
            ((ColorDrawable) lcc.get(1).getBackground()).setColor(colorListInt[7]);
            //On boucle pour les autres cases
            for (int i = 2; i<lcc.size(); i++) {
                ((ColorDrawable) lcc.get(i).getBackground()).setColor(colorListInt[i-2]);
            }

        } else if (direction.equals("-")) {
            // On s'occupe des 2 cases aux limites (non atteint par la boucle)
            ((ColorDrawable) lcc.get(6).getBackground()).setColor(colorListInt[0]);
            ((ColorDrawable) lcc.get(7).getBackground()).setColor(colorListInt[1]);
            //On boucle pour les autres cases
            for (int i = 0; i<lcc.size()-2; i++) {
                ((ColorDrawable) lcc.get(i).getBackground()).setColor(colorListInt[i+2]);
            }

        }

    }

    /**
     * Met à jour une face de la vue après une rotation quelconque.
     * @param face la face concernée
     * @param direction le sens de la rotation (+ ou -)
     */
    public void updateFaceViewFromRotation(List<TextView> face, String direction) {

        // On stocke les couleurs des cases concernées
        int[] colorListInt = new int[4];
        for (int i =0; i<face.size(); i++) {
            colorListInt[i]=((ColorDrawable) face.get(i).getBackground()).getColor();
        }

        // On attribue aux cases leur nouvelle couleur
        // 1er cas : rotation positive
        if (direction.equals("+")) {
            ((ColorDrawable) face.get(0).getBackground()).setColor(colorListInt[3]);
            for (int i = 1; i<face.size(); i++) {
                ((ColorDrawable) face.get(i).getBackground()).setColor(colorListInt[i-1]);
            }
        }
        // 2e cas : rotation négative
        else if (direction.equals("-")) {
            ((ColorDrawable) face.get(3).getBackground()).setColor(colorListInt[0]);
            for (int i = 0; i<face.size()-1; i++) {
                ((ColorDrawable) face.get(i).getBackground()).setColor(colorListInt[i+1]);
            }
        }

    }

    /**
     * Met à jour la vue et le modèle en fonction d'une des 12 rotations cliquables. Sert surtout à simplifier la méthode suivante updateCubeFromRotationButtonClick(View view).
     * @param rotation une des 12 rotation cliquables
     * @param lcc la ligne ou colonne ou centre concerné
     * @param face la face concernée
     * @param direction le sens de la rotation (+ ou -)
     */
    public void updateViewFromRotation(Rotation rotation, List<TextView> lcc, List<TextView> face, String direction) {
        // Rotation du modèle.
        cube.updateFromRotation(rotation);

        // Mise à jour de la vue.
        updateLCCViewFromRotation(lcc, direction);
        updateFaceViewFromRotation(face, direction);
    }

    /**
     * Met à jour la vue et le modèle après le clic d'un des 8 boutons de rotations.
     * @param view le bouton cliqué
     */
    public void updateCubeFromRotationButtonClick(View view) {
        // On détermine grâce au tag si c'est le bouton d'une ligne, colonne ou centre qui a été cliqué et dans quelle direction.
        String lccAndIndex = view.getTag().toString();
        String lcc = lccAndIndex.split(" ")[0];
        String index = lccAndIndex.split(" ")[1];

        // Si c'est une ligne
        if (lcc.equals("line")) {
            // D'indice 0
            if (index.equals("0")) {
                // De direction positive
                if (((Button) view).getText().equals("+")) {
                    // Alors on applique la rotation Fprime sur le modèle et on met à jour l'affichage sur la ligne et face concernées
                    updateViewFromRotation(Cube.Fprime, line0, faceB, "+");
                }
                else if (((Button) view).getText().equals("-")) {
                    updateViewFromRotation(Cube.F, line0, faceB, "-");
                }
            }
            else if (index.equals("1")) {
                if (((Button) view).getText().equals("+")) {
                    updateViewFromRotation(Cube.F, line1, faceF, "+");
                }
                else if (((Button) view).getText().equals("-")) {
                    updateViewFromRotation(Cube.Fprime, line1, faceF, "-");
                }
            }
        }
        else if (lcc.equals("column")) {
            if (index.equals("0")) {
                if (((Button) view).getText().equals("+")) {
                    updateViewFromRotation(Cube.R, column0, faceL, "+");
                }
                else if (((Button) view).getText().equals("-")) {
                    updateViewFromRotation(Cube.Rprime, column0, faceL, "-");
                }
            }
            else if (index.equals("1")) {
                if (((Button) view).getText().equals("+")) {
                    updateViewFromRotation(Cube.Rprime, column1, faceR, "+");
                }
                else if (((Button) view).getText().equals("-")) {
                    updateViewFromRotation(Cube.R, column1, faceR, "-");
                }
            }
        }
        else if (lcc.equals("center")) {
            if (index.equals("0")) {
                if (((Button) view).getText().equals("+")) {
                    updateViewFromRotation(Cube.Uprime, center0, faceU, "+");
                }
                else if (((Button) view).getText().equals("-")) {
                    updateViewFromRotation(Cube.U, center0, faceU, "-");
                }
            }
            else if (index.equals("1")) {
                if (((Button) view).getText().equals("+")) {
                    updateViewFromRotation(Cube.U, center1, faceD, "+");
                }
                else if (((Button) view).getText().equals("-")) {
                    updateViewFromRotation(Cube.Uprime, center1, faceD, "-");
                }
            }
        }
    }

    /**
     * Met à jour la vue et le modèle après le clic du bouton "Humanly !". Applique la méthode humanSolver au cube puis affiche le résultat
     * @param view le bouton cliqué ("HUMANLY !")
     */
    public void humanMethodClick(View view) {

        cube.humanSolver();
        showCube();

    }

    /**
     * Affiche l'historique des rotations effectuées sur le cube. Commence ListActivity, la 2e page.
     * @param view le bouton cliqué ("SEE THE HISTORIC")
     */
    public void showRotations(View view) {

        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(intent);

    }

    /**
     * Crée un nouveau cube puis l'affiche.
     * @param view le bouton cliqué ("NEW CUBE !")
     */
    public void newCubeClick(View view) {

        cube.randomCube();
        showCube();

    }

    /**
     * Vide l'historique des rotations effectuées sur le cube.
     * @param view le bouton cliqué ("SEE THE HISTORIC")
     */
    public void clearHistoricClick(View view) {

        cube.historicOfRotations.clear();
        cube.historicOfRotations.add("Back to main page");

    }

    /**
     * Affiche les 3 cases du cubie étalon.
     */
    public void showCubieEtalon() {

        caseD1.setBackgroundColor(Color.parseColor("white"));
        caseB0.setBackgroundColor(Color.parseColor("blue"));
        caseL0.setBackgroundColor(Color.parseColor("red"));

    }

    /**
     * Affiche les 3 cases d'un cubie.
     */
    public void showCubie(Cubie cubie) {

        // On détermine les couleurs liées au Cubie
        int posRes = cubie.getPosRes();
        List<String> colorsOfCubie = colorsOfCubies.get(posRes);

        // On détermine les 3 cases (TextView) qui seront modifiées par le Cubie à la position pos
        int pos = cubie.getPos();
        List<TextView> casesOfCubie = casesOfCubies.get(pos);

        // On détermine l'emplacement des couleurs du Cubie sur les 3 cases (Textview) puis on colorie les cases
        int ori = cubie.getOr();
        for (int i = 0; i < casesOfCubie.size(); i++) {

            casesOfCubie.get(i).setBackgroundColor(Color.parseColor(colorsOfCubie.get((i+3-ori)%3)));

        }

    }

    /**
     * Affiche toutes les cases du cube.
     */
    public void showCube() {

        // On montre chaque Cubie
        for (int i = 0; i < Cube.TAILLE; i++) {

            showCubie(cube.cubies[i]);

        }

        // On oublie pas le Cubie étalon
        showCubieEtalon();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(tsp.projetInfoGroupe2.rubikscubeprototype.R.layout.activity_main);

        // Attribution des cases
        caseB0 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseB0);
        caseB1 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseB1);
        caseB2 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseB2);
        caseB3 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseB3);

        caseU0 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseU0);
        caseU1 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseU1);
        caseU2 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseU2);
        caseU3 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseU3);

        caseF0 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseF0);
        caseF1 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseF1);
        caseF2 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseF2);
        caseF3 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseF3);

        caseD0 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseD0);
        caseD1 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseD1);
        caseD2 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseD2);
        caseD3 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseD3);

        caseR0 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseR0);
        caseR1 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseR1);
        caseR2 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseR2);
        caseR3 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseR3);

        caseL0 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseL0);
        caseL1 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseL1);
        caseL2 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseL2);
        caseL3 = (TextView) findViewById(tsp.projetInfoGroupe2.rubikscubeprototype.R.id.caseL3);

        // Attribution de la liste des cases
        cases = Arrays.asList(caseB0,caseB1,caseB2,caseB3,
                caseU0,caseU1,caseU2,caseU3,
                caseF0,caseF1,caseF2,caseF3,
                caseD0,caseD1,caseD2,caseD3,
                caseR0,caseR1,caseR2,caseR3,
                caseL0,caseL1,caseL2,caseL3);

        // Attribution des lignes, colonnes et centres
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

        // Attribution de la liste des TextView des cubies
        casesOfCubies = Arrays.asList(Arrays.asList(caseU2,caseR1,caseF3), Arrays.asList(caseU1,caseF0,caseL2), Arrays.asList(caseD0,caseL1,caseF1), Arrays.asList(caseD3,caseF2,caseR2), Arrays.asList(caseU3,caseB2,caseR0), Arrays.asList(caseU0,caseL3,caseB1), Arrays.asList(caseD2,caseR3,caseB3));

        // Attribution de la liste des couleurs des cubies
        colorsOfCubies = Arrays.asList(Arrays.asList("yellow","#FF9800","green"), Arrays.asList("yellow","green","red"), Arrays.asList("white","red","green"), Arrays.asList("white","green","#FF9800"), Arrays.asList("yellow","blue","#FF9800"), Arrays.asList("yellow","red","blue"), Arrays.asList("white","#FF9800","blue"));

        // On initialise le cube si c'est le 1er lancement de l'application
        if (cube == null) {

            cube = new Cube(new Integer[] {0,1,2,3,4,5,6}, new Integer[] {0,0,0,0,0,0,0});
            cube.randomCube();
            cube.historicOfRotations.add(0, "Back to main page");

        }

        // On montre le cube
        showCube();

    }

}
