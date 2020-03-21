import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'exécution du programme
 * @author Léandre Adam
 * @author Aydin Abiar
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Rotation> x= new ArrayList<Rotation>();
		Cube carotte = new Cube(new Integer[] {0,1,2,3,4,5,6}, new Integer[] {0,0,0,0,0,0,0});
	
		carotte.transposition(x);
		System.out.println(carotte.toString());
		System.out.println(x);
		
	}

}

//textuel
//commenter
//jUnit
//prototype qui fonctionne ( résultat qui s'affiche )