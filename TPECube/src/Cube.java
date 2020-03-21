import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Classe représentant le Cube de Rubik
 * @author Léandre Adam
 * @author Aydin Abiar
 * 
 */
public class Cube {
	
	/**
	 * 2 constantes : Un tableau des positions de chaque Cubie dans le Cube résolu; la taille du Cube = nombre de Cubies)
	 */
	final public static Integer[] POSITIONS_INITIALES = {0,1,2,3,4,5,6};
	final public static int TAILLE=7;
	
	/**
	 * 9 constantes Rotations, toutes les rotations possibles lors du jeu
	 */	
	final public static Rotation WW = new Rotation(new Integer[]{0,0,0,0,0,0,0}, new Integer[]{0,0,0,0,0,0,0},"WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");;
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
	 * Tableau des 7 Cubies du Cube
	 */
	final public Cubie[] cubies = new Cubie[TAILLE];
	@SuppressWarnings("unused")
	private boolean solved;
	
	/**
	 * Constructeur du Cube
	 * @param pos : tableau des positions des 7 Cubies
	 * @param ori : tableau des orientations des 7 Cubies
	 */
	public Cube(Integer[] posAc, Integer[] ori) {
		for (int i = 0; i<TAILLE; i++ ) {
			cubies[i] = new Cubie(POSITIONS_INITIALES[i], posAc[i], ori[i]); 
		}
	}
	
	/**
	 * Méthode appliquant une permutation sur le Cube this
	 * @param w la rotation à appliquer
	 */
	public void appliquePerm(Rotation w) {
		for (int i = 0; i<TAILLE; i++) {
			int posBeforeRotation = this.cubies[i].getPos();
			int oriBeforeRotation = this.cubies[i].getOr();
			int oriAfterRotation = w.getOrientation().get(posBeforeRotation);
			int posAfterRotation = w.getPosition().get(posBeforeRotation);
			
			this.cubies[i].setOr((oriAfterRotation + oriBeforeRotation)%3);
			this.cubies[i].setPos(posAfterRotation);
		}
	}
	
	/**
	 * Échange les deux positions des Cubies 0 et 4
	 * @param tableau des permutations déjà effectuées (vide au début éventuellement)
	 */
	public void transposition(ArrayList<Rotation> doneRotation) {
			Rotation[] perm = new Rotation[] {R,U2,Rprime,Uprime,R,U2,Rprime,F,Rprime,Fprime,R};
			for(int i=0;i<perm.length;i++) {
				this.appliquePerm(perm[i]);
				doneRotation.add(perm[i]);
			}
	}
	/**
	 * Place à leurs places les cubies du bas
	 * @param doneRotation la liste des rotations déjà effectuées
	 */
	public void placeDown(ArrayList<Rotation> doneRotation){
		List<Rotation> update = new ArrayList<Rotation>();
		if(this.cubies[2].getPos()==0) {
			update.add(F2);
		}
		else if(this.cubies[2].getPos()==1) {
			update.add(F);
			update.add(U);
			update.add(Fprime);
		}
		else if(this.cubies[2].getPos()==3) {
			update.add(F);
		}
		else if(this.cubies[2].getPos()==4) {
			update.add(R2);
			update.add(F);
		}
		else if(this.cubies[2].getPos()==5) {
			update.add(F);
			update.add(Uprime);
			update.add(Fprime);
		}
		else if(this.cubies[2].getPos()==6) {
			update.add(R);
			update.add(F);
		}
		for(int i=0;i<update.size();i++) {
			this.appliquePerm(update.get(i));
			doneRotation.add(update.get(i));
		}	
		update.clear();
		if(this.cubies[3].getPos()==0) {
			update.add(Rprime);
		}
		else if(this.cubies[3].getPos()==1) {
			update.add(Uprime);
			update.add(Rprime);
		}
		else if(this.cubies[3].getPos()==4) {
			update.add(R2);
		}
		else if(this.cubies[3].getPos()==5) {
			update.add(U2);
			update.add(Rprime);
		}
		else if(this.cubies[3].getPos()==6) {
			update.add(R);
		}
		for(int i=0;i<update.size();i++) {
			this.appliquePerm(update.get(i));
			doneRotation.add(update.get(i));
		}	
		update.clear();
		if(this.cubies[6].getPos()==0) {
			update.add(F);
			update.add(Rprime);
			update.add(Fprime);
		}
		else if(this.cubies[6].getPos()==1) {
			update.add(Rprime);
			update.add(U2);
			update.add(R);
		}
		else if(this.cubies[6].getPos()==4) {
			update.add(Rprime);
			update.add(Uprime);
			update.add(R);
		}
		else if(this.cubies[6].getPos()==5) {
			update.add(Rprime);
			update.add(U);
			update.add(R);
		}
		for(int i=0;i<update.size();i++) {
			this.appliquePerm(update.get(i));
			doneRotation.add(update.get(i));
		}	
	}
	/**
	 * Donne leur orientation aux cubies du bas
	 * @param doneRotation la liste des rotations déjà effectuées
	 */
	public void orientationDown(ArrayList<Rotation> doneRotation) {
		ArrayList<Rotation> update = new ArrayList<Rotation>();
		if(this.cubies[2].getOr()==2) {
			update.add(Fprime);
			update.add(U);
			update.add(F2);
			update.add(Uprime);
			update.add(F2);
			update.add(Uprime);
			update.add(Fprime);
			update.add(U);
			update.add(F);
			update.add(U);
			update.add(Fprime);
			update.add(Uprime);
			update.add(F2);
			update.add(Uprime);
			update.add(F2);
			update.add(U);
		}
		else if(this.cubies[2].getOr()==1) {
			update.add(F);
			update.add(U);
			update.add(F2);
			update.add(Uprime);
			update.add(F2);
			update.add(Uprime);
			update.add(Fprime);
			update.add(U);
			update.add(F);
			update.add(U);
			update.add(Fprime);
			update.add(Uprime);
			update.add(F2);
			update.add(Uprime);
			update.add(F2);
			update.add(U);
			update.add(F2);
		}
		for(int i=0;i<update.size();i++) {
			this.appliquePerm(update.get(i));
			doneRotation.add(update.get(i));
		}	
		update.clear();
		if(this.cubies[3].getOr()==1) {
			update.add(R);
			update.add(U);
			update.add(R2);
			update.add(Uprime);
			update.add(R2);
			update.add(Uprime);
			update.add(Rprime);
			update.add(U);
			update.add(R);
			update.add(U);
			update.add(Rprime);
			update.add(Uprime);
			update.add(R2);
			update.add(Uprime);
			update.add(R2);
			update.add(U);
			update.add(R2);
		}
		else if(this.cubies[3].getOr()==2) {
			update.add(Rprime);
			update.add(U);
			update.add(R2);
			update.add(Uprime);
			update.add(R2);
			update.add(Uprime);
			update.add(Rprime);
			update.add(U);
			update.add(R);
			update.add(U);
			update.add(Rprime);
			update.add(Uprime);
			update.add(R2);
			update.add(Uprime);
			update.add(R2);
			update.add(U);
		}
		for(int i=0;i<update.size();i++) {
			this.appliquePerm(update.get(i));
			doneRotation.add(update.get(i));
		}	
		update.clear();
		if(this.cubies[6].getOr()==2) {
			update.add(U);
			update.add(R2);
			update.add(Uprime);
			update.add(R2);
			update.add(Uprime);
			update.add(Rprime);
			update.add(U);
			update.add(R);
			update.add(U);
			update.add(Rprime);
			update.add(Uprime);
			update.add(R2);
			update.add(Uprime);
			update.add(R2);
			update.add(U);
			update.add(Rprime);
		}
		else if(this.cubies[6].getOr()==1) {
			update.add(R2);
			update.add(U);
			update.add(R2);
			update.add(Uprime);
			update.add(R2);
			update.add(Uprime);
			update.add(Rprime);
			update.add(U);
			update.add(R);
			update.add(U);
			update.add(Rprime);
			update.add(Uprime);
			update.add(R2);
			update.add(Uprime);
			update.add(R2);
			update.add(U);
			update.add(R);
		}
		for(int i=0;i<update.size();i++) {
			this.appliquePerm(update.get(i));
			doneRotation.add(update.get(i));
		}
	}
	/**
	 * Oriente les cubies de la deuxième couronne
	 * @param doneRotation la liste des rotations à effectuer pour résoudre le cube
	 */
	public void OLL(ArrayList<Rotation> doneRotation) {
		int[] x = {0,1,4,5};
		ArrayList<Integer>liste = new ArrayList<Integer>(Arrays.asList(this.cubies[0].getOr(),this.cubies[1].getOr(),this.cubies[4].getOr(),this.cubies[5].getOr()));
		ArrayList<Integer>listeBis = new ArrayList<Integer>();
		ArrayList<Rotation> update = new ArrayList<Rotation>();
		int s=0;
		for(int k=0;k<liste.size();k++) {
			if(liste.get(k)==0) {
				s+=1;
			}
		}
		if(s==1) {
			for ( int k : x ) {
				for(int j=0;j<TAILLE;j++) {
					if(this.cubies[j].getPos()==k) {
						listeBis.add(j);
					}
				}
			}
			while(this.cubies[listeBis.get(0)].getOr()!=0) {
				listeBis.clear();
				for ( int k : x ) {
					for(int j=0;j<TAILLE;j++) {
						if(this.cubies[j].getPos()==k) {
							listeBis.add(j);
						}
					}
				}
				this.appliquePerm(U);
				doneRotation.add(U);
			}

			update.add(R);
			update.add(U);
			update.add(Rprime);
			update.add(U);
			update.add(R);
			update.add(U2);
			update.add(Rprime);
			for(int i=0;i<update.size();i++) {
				this.appliquePerm(update.get(i));
				doneRotation.add(update.get(i));
			}	
			update.clear();
		}
		else if(s==0){
			for ( int k : x ) {
				for(int j=0;j<TAILLE;j++) {
					if(this.cubies[j].getPos()==k) {
						listeBis.add(j);
					}
				}
			}
			while(this.cubies[listeBis.get(0)].getOr()!=2 || this.cubies[listeBis.get(1)].getOr()!=1) {
				this.appliquePerm(U);
				doneRotation.add(U);
				listeBis.clear();
				for ( int k : x ) {
					for(int j=0;j<TAILLE;j++) {
						if(this.cubies[j].getPos()==k) {
							listeBis.add(j);
						}
					}
				}
			}
			update.clear();
			if(this.cubies[listeBis.get(2)].getOr()==1) {
				update.add(R2);
				update.add(U2);
				update.add(Rprime);
				update.add(U2);
				update.add(R2);
				for(int i=0;i<update.size();i++) {
					this.appliquePerm(update.get(i));
					doneRotation.add(update.get(i));
				}	
				update.clear();
			}
			else{
				update.add(U);
				update.add(F);
				update.add(R);
				update.add(U);
				update.add(Rprime);
				update.add(Uprime);
				update.add(R);
				update.add(U);
				update.add(Rprime);
				update.add(Uprime);
				update.add(Fprime);
				for(int i=0;i<update.size();i++) {
					this.appliquePerm(update.get(i));
					doneRotation.add(update.get(i));
				}	
				update.clear();
			}
		}
		else if(s==2) {
			listeBis.clear();
			update.clear();
			for ( int k : x ) {
				for(int j=0;j<TAILLE;j++) {
					if(this.cubies[j].getPos()==k) {
						listeBis.add(j);
					}
				}
			}
			if((this.cubies[listeBis.get(0)].getOr()==this.cubies[listeBis.get(3)].getOr() && this.cubies[listeBis.get(0)].getOr()==0)||(this.cubies[listeBis.get(1)].getOr()==this.cubies[listeBis.get(2)].getOr() && this.cubies[listeBis.get(1)].getOr()==0)) {
				while(this.cubies[listeBis.get(1)].getOr()!=1){
					this.appliquePerm(U);
					doneRotation.add(U);
					listeBis.clear();
					for ( int k : x ) {
						for(int j=0;j<TAILLE;j++) {
							if(this.cubies[j].getPos()==k) {
								listeBis.add(j);
							}
						}
					}
					update.add(Rprime);
					update.add(F);
					update.add(R);
					update.add(U);
					update.add(F);
					update.add(Uprime);
					update.add(Fprime);
				}
				for(int i=0;i<update.size();i++) {
					this.appliquePerm(update.get(i));
					doneRotation.add(update.get(i));
				}	
				update.clear();
			}
			else {
				while(this.cubies[listeBis.get(0)].getOr()!=this.cubies[listeBis.get(2)].getOr()) {
					this.appliquePerm(U);
					doneRotation.add(U);
					listeBis.clear();
					update.clear();
					for ( int k : x ) {
						for(int j=0;j<TAILLE;j++) {
							if(this.cubies[j].getPos()==k) {
								listeBis.add(j);
							}
						}
					}
				}
				if(this.cubies[listeBis.get(0)].getOr()!=this.cubies[listeBis.get(2)].getOr()) {
					update.add(F);
					update.add(R);
					update.add(Fprime);
					update.add(Uprime);
					update.add(Rprime);
					update.add(Uprime);
					update.add(R);
					for(int i=0;i<update.size();i++) {
						this.appliquePerm(update.get(i));
						doneRotation.add(update.get(i));
					}	
					update.clear();
				}
				else {
					update.add(F);
					update.add(R);
					update.add(U);
					update.add(Rprime);
					update.add(Uprime);
					update.add(Fprime);
					for(int i=0;i<update.size();i++) {
						this.appliquePerm(update.get(i));
						doneRotation.add(update.get(i));
					}	
					update.clear();
				}
			}
		}
	}
	/**
	 * Place correctement les cubies de la dernière couronne
	 * @param doneRotation la liste des rotations déjà effectuées
	 */
	public void PLL(ArrayList<Rotation> doneRotation) {
		List<Rotation> update = new ArrayList<Rotation>();
		while(this.cubies[0].getPos()!=0) {
			this.appliquePerm(U);
			doneRotation.add(U);
		}
		System.out.println(this.toString());
		if(this.cubies[1].getPos()==5) {
			this.appliquePerm(U2);
			doneRotation.add(U2);
			this.transposition(doneRotation);
			this.appliquePerm(U2);
			doneRotation.add(U2);
		}
		else if(this.cubies[1].getPos()==4) {
			update.add(Rprime);
			update.add(U);
			update.add(Rprime);
			update.add(F2);
			update.add(R);
			update.add(Fprime);
			update.add(U);
			update.add(Rprime);
			update.add(F2);
			update.add(R);
			update.add(Fprime);
			update.add(R);
			update.add(Uprime);
			for(int i=0;i<update.size();i++) {
				this.appliquePerm(update.get(i));
				doneRotation.add(update.get(i));
			}
		}
		if(this.cubies[5].getPos()==4) {
			this.appliquePerm(U);
			doneRotation.add(U);
			this.transposition(doneRotation);
			this.appliquePerm(U);
			doneRotation.add(U);
		}
	}
	/**
	 * Optimise la liste
	 * @param doneRotation la liste des coups
	 * @return la liste des coups optimisée renvoi2
	 */
	public ArrayList<Rotation> reduction(ArrayList<Rotation> doneRotation) {
		ArrayList<Integer> renvoi = new ArrayList<Integer>();
		ArrayList<Rotation> renvoi2 = new ArrayList<Rotation>();
		int n = doneRotation.size();
		int i=0;
		int j;
		int s;
		int w;
		for(int k=0;k<n;k++) {
			if(doneRotation.get(k)==R) {
				renvoi.add(0);
				renvoi.add(1);
			}
			else if(doneRotation.get(k)==Rprime) {
				renvoi.add(0);
				renvoi.add(3);
			}
			else if(doneRotation.get(k)==R2) {
				renvoi.add(0);
				renvoi.add(2);
			}
			else if(doneRotation.get(k)==F) {
				renvoi.add(1);
				renvoi.add(1);
			}
			else if(doneRotation.get(k)==Fprime) {
				renvoi.add(1);
				renvoi.add(3);
			}
			else if(doneRotation.get(k)==F2) {
				renvoi.add(1);
				renvoi.add(2);
			}
			else if(doneRotation.get(k)==U) {
				renvoi.add(2);
				renvoi.add(1);
			}
			else if(doneRotation.get(k)==Uprime) {
				renvoi.add(2);
				renvoi.add(3);
			}
			else if(doneRotation.get(k)==U2) {
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
	
	public ArrayList<Rotation> humanSolver(){
		ArrayList<Rotation> doneRotation = new ArrayList<Rotation>();
		this.placeDown(doneRotation);
		this.orientationDown(doneRotation);
		this.OLL(doneRotation);
		this.PLL(doneRotation);
		//doneRotation=this.reduction(doneRotation);
		return doneRotation;
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