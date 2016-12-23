package defPackage;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class TestPuzzle {
	@Test
	public void testPuzzleSolveable(){
		int [][] na = new int[4][4];
		int []inv = new int[16];
		Puzzle_Game t = new Puzzle_Game();
		Random Random_Pos_Generetor = new Random();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				na[i][j] = 0;
				inv[i * 4 + j] = 0;
		  
		 }
		}
		
		for (int i = 1; i < 16; i++) {
			int k;
			int l;
			do {
				k = Random_Pos_Generetor.nextInt(4);
				l = Random_Pos_Generetor.nextInt(4);
			} while (na[k][l] != 0);
			na[k][l] = i;
			inv[k * 4 + l] = i;
		}
		
		
		if(t.isSolvable(inv)){Assert.assertTrue("Solveable", true);}else{Assert.fail();}
	}
	
	
}
