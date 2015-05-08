package algo.generic.test;

import static org.junit.Assert.*;

import org.junit.Test;

import algo.generic.ArrayAlgo;

public class ArrayAlgoTest {

	@Test
	public void testMinimumSubArrayToSort() {
		int[] result = ArrayAlgo.minimumSubArrayToSort(new int[] {1, 2, 5, 3, 6, 4, 7, 9});
		assertArrayEquals(new int[] {2, 5}, result);
	}

	@Test
	public void testKthSmallest() {
		int actual = ArrayAlgo.kthSmallest(new int[] {2, 5, 1, 6, 8, 3}, 3);
		System.out.println(actual);
		assertEquals(3, actual);
	}

}
