package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import testInfo.Cube;
import testInfo.Rotation;

class RotationTest {

	@Test
	void testPermProd() {
		
		Rotation resultTested;
		List<Integer> listOfTruePositions;
		List<Integer> listOfTrueOrientations;
		
		// On teste permProd sur 2 rotations quelconques.
		resultTested = Cube.F.permProd(Cube.R); 
		listOfTruePositions = Arrays.asList(0,4,1,2,6,5,3);
		listOfTrueOrientations = Arrays.asList(1,2,2,1,2,0,1);
		
		for(int i = 0; i<Cube.TAILLE; i++) {
			
			if (resultTested.getPosition().get(i) != listOfTruePositions.get(i) && resultTested.getOrientation().get(i) != listOfTrueOrientations.get(i)) {
				fail("2 rotations quelconques");
			}
			
		}
		
		// On teste permProd sur 2 même rotations.
		
		resultTested = Cube.F.permProd(Cube.F);
		listOfTruePositions = Arrays.asList(2,3,0,1,4,5,6);
		listOfTrueOrientations = Arrays.asList(0,0,0,0,0,0,0);
		
		for(int i = 0; i<Cube.TAILLE; i++) {
			
			if (resultTested.getPosition().get(i) != listOfTruePositions.get(i) && resultTested.getOrientation().get(i) != listOfTrueOrientations.get(i)) {
				fail("2 mêmes rotations");
			}
			
		}
		
		// On teste permProd sur une rotation et son inverse.
		
		resultTested = Cube.F.permProd(Cube.Fprime);
		listOfTruePositions = Arrays.asList(0,1,2,3,4,5,6);
		listOfTrueOrientations = Arrays.asList(0,0,0,0,0,0,0);
		
		for(int i = 0; i<Cube.TAILLE; i++) {
			
			if (resultTested.getPosition().get(i) != listOfTruePositions.get(i) && resultTested.getOrientation().get(i) != listOfTrueOrientations.get(i)) {
				fail("rotation et son inverse");
			}
			
		}

	}

	@Test
	void testPermInv() {
		
		Rotation resultTested;
		List<Integer> listOfTruePositions;
		List<Integer> listOfTrueOrientations;
		
		// On teste permInv sur une rotation et son inverse.
		
		resultTested = Cube.F.permInv();
		listOfTruePositions = Arrays.asList(1,2,3,0,4,5,6);
		listOfTrueOrientations = Arrays.asList(2,1,2,1,0,0,0);
		
		for(int i = 0; i<Cube.TAILLE; i++) {
			
			if (resultTested.getPosition().get(i) != listOfTruePositions.get(i) && resultTested.getOrientation().get(i) != listOfTrueOrientations.get(i)) {
				fail("rotation quelconque");
			}
			
		}
		
		// On teste permInv sur la rotation identité.
		
		resultTested = new Rotation(new Integer[]{0,1,2,3,4,5,6}, new Integer[]{0,0,0,0,0,0}, "Identite");
		listOfTruePositions = Arrays.asList(0,1,2,3,4,5,6);
		listOfTrueOrientations = Arrays.asList(0,0,0,0,0,0,0);
		
		for(int i = 0; i<Cube.TAILLE; i++) {
			
			if (resultTested.getPosition().get(i) != listOfTruePositions.get(i) && resultTested.getOrientation().get(i) != listOfTrueOrientations.get(i)) {
				fail("rotation identite");
			}
			
		}
	
	}

	@Test
	void testGetPosition() {
		
		// On teste getPosition sur une rotation quelconque.
		
		Rotation resultTested = Cube.F;
		List<Integer> listOfTruePositions = Arrays.asList(3,0,1,2,4,5,6);
		
		for(int i = 0; i<Cube.TAILLE; i++) {
			
			if (resultTested.getPosition().get(i) != listOfTruePositions.get(i)) {
				fail("rotation quelconque");
			}
			
		}
		
	}

	@Test
	void testGetOrientation() {
		
		// On teste getPosition sur une rotation quelconque.
		
		Rotation resultTested = Cube.F;
		List<Integer> listOfTrueOrientations = Arrays.asList(2,1,2,1,0,0,0);
		
		for(int i = 0; i<Cube.TAILLE; i++) {
			
			if (resultTested.getOrientation().get(i) != listOfTrueOrientations.get(i)) {
				fail("rotation quelconque");
			}
			
		}
				
	}

	@Test
	void testToString() {
		// On teste getPosition sur une rotation quelconque.
		
		Rotation resultTested = Cube.F;
		String trueName = "F";
		
		if (!(resultTested.toString().contentEquals("F"))) {
			
			fail("rotation quelconque");
			
		}
		
	}

}
