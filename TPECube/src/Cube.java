
public class Cube {
	Cubie un;
	Cubie deux;
	Cubie trois;
	Cubie quatre;
	Cubie cinq;
	Cubie six;
	Cubie sept;
	boolean solved;
	Rotation R = new Rotation(5,2,3,1,7,6,4,1,0,0,2,2,0,1);
	Rotation F = new Rotation(4,1,2,3,5,6,7,2,1,2,1,0,0,0);
	Rotation U = new Rotation(2,6,3,4,1,5,7,0,0,0,0,0,0,0);
	Rotation Fprime = new Rotation();
	Rotation Uprime = new Rotation();
	Rotation Rprime = new Rotation();
	Rotation R2 = R.permProd(R);
	Rotation U2 = U.permProd(U);
	Rotation F2 = F.permProd(F);
	
	public Cube() {
	}
}
