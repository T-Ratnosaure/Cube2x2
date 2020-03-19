/**
 * Classe d'exécution du programme
 * @author Léandre Adam
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		Rotation R = new Rotation(5,2,3,1,7,6,4,1,0,0,2,2,0,1);
		@SuppressWarnings("unused")
		Rotation F = new Rotation(4,1,2,3,5,6,7,2,1,2,1,0,0,0);
		@SuppressWarnings("unused")
		Rotation U = new Rotation(2,6,3,4,1,5,7,0,0,0,0,0,0,0);
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
		
		System.out.println(R.permProd(F).position);
		System.out.println(R.permProd(F).orientation);
		
		
		Cube carotte = new Cube(1,2,3,4,5,6,7,0,0,0,0,0,0,0);
		//System.out.println(carotte.toString());
		//System.out.println(R.position);
		//System.out.println(R.orientation);
		carotte.appliquePerm(R);
		System.out.println(carotte.toString());
		carotte.appliquePerm(F);
		System.out.println(carotte.toString());
	}

}

//textuel
//commenter
//jUnit
//prototype qui fonctionne ( résultat qui s'affiche )