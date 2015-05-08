package algo.dynamicprogramming;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditDistanceTest {

	String source;
	String target;
	
	@Before
	public void setUp() throws Exception {
		source = "abcd";
		target = "bccd";
	}

	@After
	public void tearDown() throws Exception {
		source = null;
		target = null;
	}

	@Test
	public void testEditDistanceRecursive() {
		assertEquals(2, EditDistance.editDistanceDP(source, target));
	}

	@Test
	public void testLongestCommonSubsequence() {
		assertEquals(3, EditDistance.longestCommonSubsequence(source, target));
	}
	
	@Test
	public void testKnapsack() {
		int val[] = {60, 100, 120};
	    int wt[] = {10, 20, 30};
	    int  W = 50;
	    
	    assertEquals(220, EditDistance.knapsack(wt, val, W, val.length));
	}
}