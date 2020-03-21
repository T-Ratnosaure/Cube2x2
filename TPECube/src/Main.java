import java.util.ArrayList;

/**
 * Classe d'exécution du programme
 * @author Léandre Adam
 * @author Aydin Abiar
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unused")
		Rotation R = new Rotation(new Integer[]{4,1,2,0,6,5,3}, new Integer[]{1,0,0,2,2,0,1},"R");
		@SuppressWarnings("unused")
		Rotation F = new Rotation(new Integer[]{3,0,1,2,4,5,6},new Integer[]{2,1,2,1,0,0,0},"F");
		@SuppressWarnings("unused")
		Rotation U = new Rotation(new Integer[]{1,5,2,3,0,4,6},new Integer[]{0,0,0,0,0,0,0},"U");
		@SuppressWarnings("unused")
		Rotation Fprime = F.permInv();
	    @SuppressWarnings("unused")
		Rotation Uprime = U.permInv();
		@SuppressWarnings("unused")
		Rotation Rprime = R.permInv();
		@SuppressWarnings("unused")
		Rotation R2 = R.permProd(R);
		@SuppressWarnings("unused")
		Rotation U2 = U.permProd(U);
		@SuppressWarnings("unused")
		Rotation F2 = F.permProd(F);
		
		
		Cube carotte = new Cube(new Integer[] {0,1,2,3,4,5,6}, new Integer[] {0,0,0,0,0,0,0});
		//System.out.println(carotte.toString());
		//System.out.println(R.position);
		//System.out.println(R.orientation);
		/* carotte.appliquePerm(R);
		System.out.println(carotte.toString());
		carotte.appliquePerm(F);
		System.out.println(carotte.toString());
		*/
		ArrayList<Rotation> rotations = new ArrayList<Rotation>();
		carotte.transposition(new ArrayList<Rotation>());
		carotte.appliquePerm(F);
		carotte.appliquePerm(R);
		carotte.appliquePerm(Uprime);
		carotte.appliquePerm(F);
		carotte.appliquePerm(U);
		rotations.clear();
		rotations=carotte.humanSolver();
		System.out.println(carotte.toString());
		for(int i=0;i<rotations.size();i++) {
			System.out.println(rotations.get(i).toString());
		}
		System.out.println(rotations.size());
		
	}

}

//textuel
//commenter
//jUnit
//prototype qui fonctionne ( résultat qui s'affiche )