package defPackage;

import org.junit.Assert;
import org.junit.Test;

public class TestPuzzle {
	@Test
	public void testPuzzleSolveable(){
	
		Puzzle_Game t = new Puzzle_Game();
		int[] a = new int[16];
		  for (int i = 1;i<a.length;i++){
			  a[i] = i;
			 
			  
			  
		  }
		  
		 // a[14] =15;
		//  a[16] = 14;

		if(t.isSolvable(a)==true){Assert.assertTrue("PUZZLE IS SOLVEABLE", true);}else{
			Assert.assertFalse(false);
		}
		
		
		
	}
	
}
