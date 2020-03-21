import java.util.ArrayList;

/**
 * Classe d'exécution du programme
 * @author Léandre Adam
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		Rotation R = new Rotation(new Integer[]{4,1,2,0,6,5,3}, new Integer[]{1,0,0,2,2,0,1});
		@SuppressWarnings("unused")
		Rotation F = new Rotation(new Integer[]{3,0,1,2,4,5,6},new Integer[]{2,1,2,1,0,0,0});
		@SuppressWarnings("unused")
		Rotation U = new Rotation(new Integer[]{1,5,2,3,0,4,6},new Integer[]{0,0,0,0,0,0,0});
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
		
		System.out.println(R.permProd(F).getPosition());
		System.out.println(R.permProd(F).getOrientation());
		
		
		Cube carotte = new Cube(new Integer[] {0,1,2,3,4,5,6}, new Integer[] {0,0,0,0,0,0,0});
		//System.out.println(carotte.toString());
		//System.out.println(R.position);
		//System.out.println(R.orientation);
		/* carotte.appliquePerm(R);
		System.out.println(carotte.toString());
		carotte.appliquePerm(F);
		System.out.println(carotte.toString());
		*/
		ArrayList<Rotation> rotations = carotte.transposition(new ArrayList<Rotation>());
		System.out.println(carotte.toString());
		
	}

}

//textuel
//commenter
//jUnit
//prototype qui fonctionne ( résultat qui s'affiche )
